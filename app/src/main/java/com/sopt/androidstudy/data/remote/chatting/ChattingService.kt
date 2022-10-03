package com.sopt.androidstudy.data.remote.chatting

import com.sopt.androidstudy.data.model.chatting.Messages
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChattingService {
    @GET("/api/message/{roomId}")
    suspend fun getChattingList(
        @Path("roomId") roomId: String,
    ): Response<Messages>
}