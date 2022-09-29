package com.sopt.androidstudy.data.remote.chatting

import com.sopt.androidstudy.data.model.chatting.Messages
import com.sopt.androidstudy.data.util.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ChattingService {
    @GET("/api/message/{roomId}")
    suspend fun getChattingList(
        @Path("roomId") roomId: String,
    ): BaseResponse<Messages>
}