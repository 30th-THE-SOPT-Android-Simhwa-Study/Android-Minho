package com.sopt.androidstudy.service

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import timber.log.Timber
import java.nio.charset.StandardCharsets


class OkHttpWebSocketListener : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
        Timber.e("Open $response")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        Timber.e("Closed")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Timber.e("Closing")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Timber.e("Failure ${t.printStackTrace()} $response")
        webSocket.send("Hi")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Timber.e(
            "Get Message ${
                bytes.string(StandardCharsets.UTF_8)
                
            }"
        )
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        Timber.e(
            "Get Message!!!! $text"
        )
    }
}