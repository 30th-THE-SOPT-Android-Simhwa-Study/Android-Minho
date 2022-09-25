package com.sopt.androidstudy.presentation.save.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sopt.androidstudy.R
import com.sopt.androidstudy.data.model.UserData
import com.sopt.androidstudy.data.model.db.Friend
import com.sopt.androidstudy.databinding.ActivitySaveBinding
import com.sopt.androidstudy.presentation.save.adapter.FriendRecyclerViewAdapter
import com.sopt.androidstudy.presentation.save.viewmodels.FriendViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.client.Socket.*
import io.socket.emitter.Emitter
import io.socket.engineio.client.EngineIOException
import java.net.MalformedURLException

@AndroidEntryPoint
class FriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveBinding
    private val friendViewModel: FriendViewModel by viewModels()
    private lateinit var friendAdapter: FriendRecyclerViewAdapter
    private lateinit var socket: Socket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.getParcelableExtra<UserData>("userData")
        //로그인시 내 계정 정보 받아오기. 아직은 안씀
        initBindingView()
        displayFriendsList()
        //ClientThread().start()
    }


    private fun initBindingView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_save)
        binding.myViewModel = friendViewModel
        binding.lifecycleOwner = this
        friendAdapter = FriendRecyclerViewAdapter(::selectFriend)
        try {
            socket = IO.socket("http://13.125.232.150:3000")


        }catch (e: MalformedURLException){
            e.printStackTrace()
        }
        socket.on(EVENT_CONNECT) {
            Log.i("Socket", "Connect")
        }.on(EVENT_DISCONNECT) { args ->
            Log.i("Socket", "Disconnet: ${args[0]}")
        }.on(EVENT_CONNECT_ERROR) { args ->
            var errorMessage = ""
            if (args[0] is EngineIOException) {
                errorMessage = "code: ${args[0]}"
            }
            Log.i("Socket", "Connect Error: $errorMessage")
        }
        socket.connect()
        initEvent()
        binding.mainRcv.adapter = friendAdapter
    }

    val emitOn = Emitter.Listener {
        Log.d("why?", it.toString())
        binding.mbtiText.text = it[0].toString()
    }

    private fun initEvent() {
        friendViewModel.showToast.observe(this) {
            it.getContentIfNotHandled()?.let {
                if (friendViewModel.isInsertSuccess.value == true)
                    Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show() else Toast.makeText(
                    this,
                    "exception : invalid email type",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }/*
    inner class ClientThread : Thread() {
        override fun run() {
            val host = "10.2.2"
            val port = 8000
            try {
                val socket = Socket(host, port)
                val outstream = ObjectOutputStream(socket.getOutputStream())
                outstream.writeObject("Hello!")
                outstream.flush()
                Log.d("ClientStream", "Sent to server.")
                val instream = ObjectInputStream(socket.getInputStream())
                val input: Any = instream.readObject()
                Log.d("ClientThread", "Received data: $input")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }*/

    private fun selectFriend(friend: Friend) {
        friendViewModel.selectFriend(friend = friend)
        //이전 기능 포함
        val intent = Intent(this@FriendActivity, FriendGithubActivity::class.java).apply {
            putExtra("username", "KkamSonLee")
        }
        startActivity(intent)
    }

    private fun displayFriendsList() {
        friendViewModel.friends.observe(this) {
            friendAdapter.submitList(it)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        socket.disconnect()
    }

}