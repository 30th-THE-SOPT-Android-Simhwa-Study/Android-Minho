package com.sopt.androidstudy.data.datasources

import com.sopt.androidstudy.data.model.chatting.ChatDto
import com.sopt.androidstudy.data.model.chatting.Messages
import com.sopt.androidstudy.data.remote.chatting.ChattingService
import com.sopt.androidstudy.data.util.BaseResponse
import javax.inject.Inject

class RemoteChattingDataSource @Inject constructor(private val chattingService: ChattingService) {

    suspend fun getChattingList(roomId: String): BaseResponse<Messages> =
        chattingService.getChattingList(roomId)
}