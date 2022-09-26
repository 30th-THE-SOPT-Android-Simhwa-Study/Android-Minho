package com.sopt.androidstudy.data.remote.chatting

import com.sopt.androidstudy.data.model.chatting.ChatDto
import com.sopt.androidstudy.data.util.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ChattingService {
    @GET("/{roomId}")
    suspend fun getChattingList(
        @Path("roomId") roomId:Int
    ):BaseResponse<List<ChatDto>>
}