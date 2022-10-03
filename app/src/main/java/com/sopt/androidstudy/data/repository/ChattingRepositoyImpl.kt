package com.sopt.androidstudy.data.repository

import com.sopt.androidstudy.data.datasources.RemoteChattingDataSource
import com.sopt.androidstudy.data.mappers.ChattingMapper
import com.sopt.androidstudy.data.model.chatting.Messages
import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.domain.repository.ChattingRepository
import com.sopt.androidstudy.domain.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChattingRepositoyImpl @Inject constructor(
    private val dataSource: RemoteChattingDataSource,
    private val mapper: ChattingMapper
) : ChattingRepository {
    override suspend fun getChattingList(roomId: String): ApiResult<List<ChatEntity>?> {
        val result = dataSource.getChattingList(roomId)
        return if (result.isSuccessful) {
            ApiResult.Success(mapper.map(result.body()?.messages!!))
        } else {
            ApiResult.Failure(result.code().toString())
        }
    }
}
