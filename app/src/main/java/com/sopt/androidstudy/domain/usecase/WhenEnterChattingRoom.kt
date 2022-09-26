package com.sopt.androidstudy.domain.usecase

import com.sopt.androidstudy.domain.util.ApiResult
import com.sopt.androidstudy.presentation.model.chating.Chat
import kotlinx.coroutines.flow.Flow

interface WhenEnterChattingRoom {
    fun getAllChattingList(roomId:String):Flow<ApiResult<List<Chat>>>
}