package com.sopt.androidstudy.domain.repository

import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.domain.util.ApiResult

interface ChattingRepository {
    suspend fun getChattingList(roomId: String): ApiResult<List<ChatEntity>?>
}