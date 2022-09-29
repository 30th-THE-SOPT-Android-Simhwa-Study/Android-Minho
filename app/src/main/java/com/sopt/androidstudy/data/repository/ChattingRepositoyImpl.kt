package com.sopt.androidstudy.data.repository

import com.sopt.androidstudy.data.datasources.RemoteChattingDataSource
import com.sopt.androidstudy.data.mappers.ChattingMapper
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
    override fun getChattingList(roomId: String): Flow<ApiResult<List<ChatEntity>>> = flow {
        val data = dataSource.getChattingList(roomId)
        emit(ApiResult.Loading(true))
        if (!data.success) {
            emit(ApiResult.Failure(data.message))
        } else if (data.data == null) {
            emit(ApiResult.Empty(true))
        } else {
            emit(ApiResult.Loading(false))
            emit(ApiResult.Success(mapper.map(data.data.messages)))
        }
    }.flowOn(Dispatchers.IO)
}