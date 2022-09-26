package com.sopt.androidstudy.domain.repository

import com.sopt.androidstudy.domain.entity.ChatEntity
import kotlinx.coroutines.flow.Flow

interface ChattingRepository {
    fun getChattingList(roomId:String): Flow<List<ChatEntity>>
}