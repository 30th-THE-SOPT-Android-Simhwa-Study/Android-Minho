package com.sopt.androidstudy.data.datasources

import com.sopt.androidstudy.data.model.chatting.ChatDto
import com.sopt.androidstudy.data.remote.chatting.ChattingService
import com.sopt.androidstudy.data.util.BaseResponse

class RemoteChattingDataSource(val chattingService: ChattingService) {

    suspend fun getChattingList(roomId: Int): BaseResponse<List<ChatDto>> =
        chattingService.getChattingList(roomId)
}