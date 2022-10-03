package com.sopt.androidstudy.domain.usecase

import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface WhenEnterChattingRoom {
    suspend fun getAllChattingList(roomId:String):Flow<List<ChatEntity>?>
}