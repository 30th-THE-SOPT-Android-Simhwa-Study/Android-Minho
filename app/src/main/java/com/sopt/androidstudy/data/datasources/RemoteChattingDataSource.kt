package com.sopt.androidstudy.data.datasources

import com.sopt.androidstudy.data.model.chatting.Messages
import com.sopt.androidstudy.data.remote.chatting.ChattingService
import retrofit2.Response
import javax.inject.Inject

class RemoteChattingDataSource @Inject constructor(private val chattingService: ChattingService) {

    suspend fun getChattingList(roomId: String): Response<Messages> =
        chattingService.getChattingList(roomId)
}