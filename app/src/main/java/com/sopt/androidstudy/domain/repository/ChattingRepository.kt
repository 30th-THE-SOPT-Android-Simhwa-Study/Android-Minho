package com.sopt.androidstudy.domain.repository

import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface ChattingRepository {
    fun getChattingList(roomId:String): Flow<ApiResult<List<ChatEntity>>>
}