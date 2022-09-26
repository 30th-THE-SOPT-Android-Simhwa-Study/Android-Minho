package com.sopt.androidstudy.data.repository

import com.sopt.androidstudy.data.datasources.RemoteChattingDataSource
import com.sopt.androidstudy.data.mappers.ChattingMapper
import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.domain.repository.ChattingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChattingRepositoyImpl @Inject constructor(private val dataSource: RemoteChattingDataSource, private val mapper: ChattingMapper) :
    ChattingRepository {
    override suspend fun getChattingList(roomId: Int): Flow<List<ChatEntity>> = flow {
        dataSource.getChattingList(roomId).data?.let {
            emit(mapper.map(it))
        }
    }
}