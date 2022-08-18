package com.sopt.androidstudy.presentation.save.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import android.util.Base64
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivityFriendGithubBinding
import com.sopt.androidstudy.presentation.save.screens.fragment.Follwer
import com.sopt.androidstudy.presentation.save.screens.fragment.Repo
import com.sopt.androidstudy.presentation.save.viewmodels.FriendGithubViewModel
import kotlinx.coroutines.launch
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


class FriendGithubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendGithubBinding
    private val myFragments =
        listOf(Follwer(), Repo())
    lateinit var user: String
    var isEnabled = true
    val friendGithubViewModel: FriendGithubViewModel by viewModels()

    //For Thread
    var i = 0
    val countThread = thread(start = false) {
        while (true) {
            i++
            val bundle = Bundle()
            val message = myHandler.obtainMessage()
            SystemClock.sleep(1000)
            bundle.putInt("count", i)
            message.data = bundle
            message.what = COUNT_WHAT
            myHandler.sendMessage(message)
        }
    }

    val myHandler = MyHandler()
    val backgroundHadlerThread = HandlerThread("HandlerThread")
    lateinit var myThreadHandler: MyThreadHandler
    //val background1Thread = Background1Thread(myHandler)


    //lateinit var socket: Socket
    //val myThread: ExecutorService = Executors.newFixedThreadPool(8)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = intent.getStringExtra("username").toString()
        //ServerThread().start()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend_github)
        user.let {
            friendGithubViewModel.setUserName(user)
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    friendGithubViewModel.getUserData.collect {
                        Log.d("Collect!!", it.toString())
                        binding.user = it
                    }
                }
            }
        }
        backgroundHadlerThread.start()
        myThreadHandler = MyThreadHandler()
        binding.imageView.setOnClickListener {
            if (countThread.isAlive) {
                i = 0
                Log.d("is Alive", countThread.toString())
                countThread.interrupt()
                if (!countThread.isAlive) {
                    countThread.start()
                }
            } else {
                countThread.start()
            }
        }
        bindingView()
        friendGithubViewModel.selectUser.observe(this) {
            val bundle = Bundle()
            bundle.putString("string", it.avatar_url)
            val message = myThreadHandler.obtainMessage().apply {
                        data = bundle
                        what = IMAGE_WHAT
            }
            myThreadHandler.sendMessage(message)
        }
    }

    private fun bindingView() {
        /*socket = BindingConversions.get()
        socket.connect()*/
        binding.btnFollower.isEnabled = !isEnabled
        binding.btnRepo.isEnabled = isEnabled
        supportFragmentManager.beginTransaction()
            .add(R.id.fragContainerGithub, myFragments[0])
            .commit()
        binding.btnFollower.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainerGithub, myFragments[0])
                .commit()
            isEnabled = false
            binding.btnFollower.isEnabled = isEnabled
            binding.btnRepo.isEnabled = !isEnabled
        }
        binding.btnRepo.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainerGithub, myFragments[1])
                .commit()
            isEnabled = true
            binding.btnFollower.isEnabled = isEnabled
            binding.btnRepo.isEnabled = !isEnabled
        }
    }

    inner class MyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val bundle = msg.data
            when (msg.what) {
                COUNT_WHAT -> {
                    val count = bundle.getInt("count")
                    binding.count.text = count.toString()
                }
                IMAGE_WHAT -> {
                    val stringBitmap = bundle.getString("bitmap") ?: return
                    binding.profileImage.post {
                        binding.profileImage.setImageBitmap(ConvertBitmap().stringToBitmap(stringBitmap))
                    }
                }
            }
        }
    }

    inner class MyThreadHandler : Handler(backgroundHadlerThread.looper) {
        override fun handleMessage(msg: Message) {
            val bundle: Bundle = msg.data
            if (msg.what == IMAGE_WHAT) {
                val stringBitmap = bundle.getString("string")
                val bitmap =
                    stringBitmap?.let { convertBitmap.getBitmapFromURL(it) }
                stringBitmap?.let {
                    binding.imageView.post {
                        binding.imageView.setImageBitmap(bitmap)
                    }
                }
                SystemClock.sleep(1000)
            }
        }
    }

    /*inner class Background2Thread : Thread() {
        override fun run() {
            val bitmap =
                convertBitmap.getBitmapFromURL("https://avatars.githubusercontent.com/u/15981307?v=4")
            val bundle = Bundle()
            val message = myHandler.obtainMessage()
            bundle.putString("bitmap",
                bitmap?.let { it1 -> convertBitmap.bitmapToString(it1) })
            message.data = bundle
            myHandler.sendMessage(message)
        }
    }

    inner class ServerThread : Thread() {
        override fun run() {
            val port = 8000
            try {
                val server = ServerSocket(port)
                Log.d("ServerThread", "Server Started.")
                while (true) {
                    val socket: java.net.Socket = server.accept()
                    val instream = ObjectInputStream(socket.getInputStream())
                    val input: Any = instream.readObject()
                    Log.d("ServerThread", "input: $input")
                    val outstream = ObjectOutputStream(socket.getOutputStream())
                    outstream.writeObject("$input from server.")
                    outstream.flush()
                    Log.d("ServerThread", "output sent.")
                    socket.close()
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }*/

    companion object {
        const val COUNT_WHAT = 1
        const val IMAGE_WHAT = 2
    }
}

object convertBitmap {
    fun getBitmapFromURL(src: String): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun bitmapToString(bitmap: Bitmap): String {
        val baos =
            ByteArrayOutputStream() //바이트 배열을 차례대로 읽어 들이기위한 ByteArrayOutputStream클래스 선언
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos) //bitmap을 압축 (숫자 70은 70%로 압축한다는 뜻)
        val bytes = baos.toByteArray() //해당 bitmap을 byte배열로 바꿔준다.
        return Base64.encodeToString(bytes, Base64.DEFAULT) //Base 64 방식으로byte 배열을 String으로 변환
    }

    fun stringToBitmap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte: ByteArray = Base64.decode(
                encodedString,
                Base64.DEFAULT
            ) // String 화 된 이미지를  base64방식으로 인코딩하여 byte배열을 만듬
            BitmapFactory.decodeByteArray(
                encodeByte,
                0,
                encodeByte.size
            ) //byte배열을 bitmapfactory 메소드를 이용하여 비트맵으로 바꿔준다.
            //만들어진 bitmap을 return
        } catch (e: Exception) {
            e.message
            null
        }
    }
}

/*class myThreadHandler:Runnable{
    override fun run() {
        Looper.prepare()

        val bitmap =
        try {
            val url = URL("https://avatars.githubusercontent.com/u/90037701?v=4")
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}*/
/*fun uploadUserData(avatarUrl: String) {
        var bitmap: Bitmap? = null
        //Thread(myRunnable())

        *//* myThread.submit {
             bitmap = getBitmapFromURL(avatarUrl)
             //Log.d("Bitmap", bitmap.toString())
             binding.imageView.post {
                 bitmap?.let {
                     binding.imageView.setImageBitmap(it)
                 }
                 //binding.imageView.setImageBitmap(call.call())
             }
         }*//*
        //Thread{}
        *//*val call= myThread.submit {
            Callable {
                getBitmapFromURL(avatarUrl)
            }
        }
        call.get()?.let {
            runOnUiThread {
                Log.d("get!!!!!", it.toString())
                binding.imageView.setImageBitmap(it as Bitmap)
            }
        }*//*
    }*/