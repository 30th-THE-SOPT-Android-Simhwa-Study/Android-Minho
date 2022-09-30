package com.sopt.androidstudy.service

import com.sopt.androidstudy.di.ServiceModule
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.util.concurrent.TimeUnit

class SocketService(private val webSocketListener: OkHttpWebSocketListener) {

    private val client = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()
    private val request = Request.Builder().url("http://13.125.232.150:3000").build()
    fun getSocket(): WebSocket = client.newWebSocket(request, webSocketListener)
}