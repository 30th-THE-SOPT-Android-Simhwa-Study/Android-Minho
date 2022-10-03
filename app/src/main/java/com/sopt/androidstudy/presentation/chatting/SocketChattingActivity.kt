package com.sopt.androidstudy.presentation.chatting

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.sopt.androidstudy.R
import com.sopt.androidstudy.databinding.ActivitySocketChattingBinding
import com.sopt.androidstudy.di.ServiceModule
import com.sopt.androidstudy.domain.util.ApiResult
import com.sopt.androidstudy.presentation.base.BaseActivity
import com.sopt.androidstudy.presentation.mapper.chatting.ChattingListASCMapper
import com.sopt.androidstudy.presentation.mapper.chatting.MessageToChatMapper
import com.sopt.androidstudy.presentation.model.chating.Chat
import com.sopt.androidstudy.presentation.state.UiState
import com.sopt.androidstudy.presentation.util.collectFlowWhenStarted
import com.sopt.androidstudy.service.Message
import com.sopt.androidstudy.service.OkHttpWebSocketListener
import dagger.hilt.android.AndroidEntryPoint
import dev.gustavoavila.websocketclient.WebSocketClient
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.emitter.Emitter
import io.socket.engineio.client.EngineIOException
import io.socket.engineio.client.Transport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody
import timber.log.Timber
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URI
import java.net.URISyntaxException
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class SocketChattingActivity :
    BaseActivity<ActivitySocketChattingBinding>(R.layout.activity_socket_chatting) {

    private val viewModel: ChattingViewModel by viewModels()
    private lateinit var adapter: ChattingMultiHolderAdapter
    private lateinit var webSocket: WebSocket
    private lateinit var socket: Socket
    private lateinit var httpSocket: java.net.Socket
    private lateinit var gson: Gson
    private lateinit var uri:URI

    @Inject
    lateinit var chatMapper: MessageToChatMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        onBind()
        collectList()
        gson = Gson()

        //connectSocketIO()
        connectWebSocketDiff()
        //connectSocket()

    }

    private fun connectSocketIO() {
        try {
/*
            val options = IO.Options()
            val client = OkHttpClient.Builder()
                .addInterceptor(ServiceModule.loggingInterceptor)
                .addInterceptor(ServiceModule.AuthInterceptor())
                .build()
            options.webSocketFactory = client
            val headers = mapOf<String, String>(Pair("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE2OCIsImVtYWlsIjoic2V1bmdoZW9uMzI4QGdtYWlsLmNvbSIsImlhdCI6MTY2NDQ0MDAzNSwiZXhwIjoxNjk1OTk3NjM1LCJpc3MiOiJwbGF5dG9nZXRoZXIifQ.ljT7GMJhM1iKx7G34vVdD_s6AWax0nQHao1Rvne3t6Q"))
            options.auth = headers*/
            socket = IO.socket("http://13.125.232.150:3000")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        val emitOn = Emitter.Listener {


        }
        socket.on(Socket.EVENT_CONNECT) { args ->
            Timber.e("Event connect code: $args")
        }
        /*socket.io().on(Manager.EVENT_TRANSPORT) { args ->
            val transport: Transport = args[0] as Transport
            transport.on(Transport.EVENT_REQUEST_HEADERS, Emitter.Listener { args ->
                val headers = args[0] as MutableMap<String, String>
                headers["authorization"] =
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE2OCIsImVtYWlsIjoic2V1bmdoZW9uMzI4QGdtYWlsLmNvbSIsImlhdCI6MTY2NDQ0MDAzNSwiZXhwIjoxNjk1OTk3NjM1LCJpc3MiOiJwbGF5dG9nZXRoZXIifQ.ljT7GMJhM1iKx7G34vVdD_s6AWax0nQHao1Rvne3t6Q"
            })
        }*/
        socket.on(Socket.EVENT_DISCONNECT) { args ->
            Log.i("Socket", "Disconnet: ${args[0]}")
        }
        socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
            var errorMessage = ""
            if (args[0] is EngineIOException) {
                errorMessage = "code: ${args[0]}"
            }
            Timber.e("Socket Connect Error: $errorMessage")
        }
        socket.on("resConnection") { args ->
            Timber.e("res connect code: ${args[0]}")
            val data = gson.toJson(EnterRoomRequest(55, 57))
            socket.emit("reqEnterRoom", data)
        }
        socket.on("reqConnection") { args ->
            Timber.e("req connect code: ${args[0]}")
        }
        socket.on("resEnterRoom") {
            Timber.e("Response ${it[0]}")
            socket.emit("reqSendMessage", gson.toJson(RequestSendMessage(55, "Hi")))
        }
        socket.on("resSendMessage") { args ->
                Timber.e("send Message Response ${args[0]}")
        }
        socket.connect()
    }


    fun connectWebSocketDiff() {

        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addNetworkInterceptor(ServiceModule.AuthInterceptor())
            .build()
            //.addInterceptor(ServiceModule.loggingInterceptor)

        val request =
            Request.Builder().url("ws://13.125.232.150:3000/socket.io/?EIO=4&transport=websocket&sid=WgSru-MNFQqbpbM6AAFk").build()

        val request2 = Request.Builder()
            .url("ws://13.125.232.150:3000/socket.io/?EIO=4&transport=websocket&sid=WgSru-MNFQqbpbM6AAFk")
            .addHeader(
                "Authorization",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE0OSIsImVtYWlsIjoibHNoMzI4MzI4QG5hdmVyLmNvbSIsImlhdCI6MTY2MzI0NzUyOCwiZXhwIjoxNjY0NDU3MTI4LCJpc3MiOiJwbGF5dG9nZXRoZXIifQ.M62jThpT65_8tSTW35x8hWrYaT56PJ7rOONSsbwqAK0"
            ).addHeader(
                "Origin",
                "http//13.125.232.150"
            )
            .build()

        try {
            uri = URI.create("ws://13.125.232.150:3000")
        }catch (e:URISyntaxException){
            e.printStackTrace()
        }
        val webSocketClient = object :WebSocketClient(uri){
            override fun onOpen() {
                Timber.e("Open")
            }

            override fun onTextReceived(message: String?) {
                Timber.e("Received Text $message")
            }

            override fun onBinaryReceived(data: ByteArray?) {
                Timber.e("Received Binary $data")
            }

            override fun onPingReceived(data: ByteArray?) {
                Timber.e("Received Ping $data")
            }

            override fun onPongReceived(data: ByteArray?) {
                Timber.e("Received Pong $data")
            }

            override fun onException(e: Exception?) {
                Timber.e("Exception ${e?.printStackTrace()}")
            }

            override fun onCloseReceived() {
                Timber.e("Closed")
            }

        }
        webSocketClient.setConnectTimeout(10000);
        webSocketClient.setReadTimeout(60000);
        webSocketClient.addHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE2OCIsImVtYWlsIjoic2V1bmdoZW9uMzI4QGdtYWlsLmNvbSIsImlhdCI6MTY2NDQ0MDAzNSwiZXhwIjoxNjk1OTk3NjM1LCJpc3MiOiJwbGF5dG9nZXRoZXIifQ.ljT7GMJhM1iKx7G34vVdD_s6AWax0nQHao1Rvne3t6Q");
        webSocketClient.enableAutomaticReconnection(5000);
        //webSocketClient.connect();
        webSocket = client.newWebSocket(request2, OkHttpWebSocketListener())
        webSocket.send("Test Message")
        client.dispatcher.executorService.shutdown()
    }

    private suspend fun connectWebSocket(): String = withContext(Dispatchers.IO) {

        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder().url("ws://13.125.232.150:3000").addHeader(
            "Authorization",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE0OSIsImVtYWlsIjoibHNoMzI4MzI4QG5hdmVyLmNvbSIsImlhdCI6MTY2MzI0NzUyOCwiZXhwIjoxNjY0NDU3MTI4LCJpc3MiOiJwbGF5dG9nZXRoZXIifQ.M62jThpT65_8tSTW35x8hWrYaT56PJ7rOONSsbwqAK0"
        ).addHeader(
            "Origin",
            "http//13.125.232.150"
        ).build()
        try {
            val response = client.newCall(request).execute()
            response.message
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            e.toString()
        }


    }


    private fun connectSocket() {

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                httpSocket = java.net.Socket("http://13.125.232.150", 3000)

                Timber.e("$httpSocket")
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun collectList() {
        collectFlowWhenStarted(viewModel.chattingList) {
            when (it) {
                is UiState.Idle -> {
                    Timber.e("Init!!!!!")
                }
                is UiState.Loading -> {
                    if (it.isLoading) {
                        Timber.e("Loading!!!!!!!!")
                    } else {
                        Timber.e("Clear Loading!!!!!")
                    }
                }
                is UiState.Success -> {
                    Timber.e("Success!!!!!!!!")
                    if (it.datas.isNotEmpty()) {
                        adapter.submitList(it.datas)
                        binding.rcChattingList.smoothScrollToPosition(adapter.itemCount - 1)
                    }
                }
                is UiState.Empty -> {
                }
                is UiState.Failure -> {
                    Timber.e("Failure!!!!!!!! ${it.throwable}")
                }
                else -> {}
            }
        }
    }

    fun addChatMessage(chat: Any) {
        when (chat) {
            is Message -> {
                adapter.addChattingMessage(chatMapper.map(chat))
            }
            is Chat -> {
                adapter.addChattingMessage(chat)
            }
        }
        binding.rcChattingList.scrollToPosition(adapter.itemCount - 1)
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocket.send("disconnect")
        webSocket.cancel()
        //socket.disconnect()
    }

    private fun onBind() {
        adapter = ChattingMultiHolderAdapter()
        binding.rcChattingList.adapter = adapter
    }
}