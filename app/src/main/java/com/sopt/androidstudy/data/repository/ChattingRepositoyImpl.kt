package com.sopt.androidstudy.data.repository

import com.sopt.androidstudy.data.datasources.RemoteChattingDataSource
import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.domain.repository.ChattingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChattingRepositoyImpl(val dataSource: RemoteChattingDataSource):ChattingRepository {
    override suspend fun getChattingList(roomId: Int): Flow<List<ChatEntity>> = flow{
        dataSource.getChattingList(roomId).data?.let {

        }
    }
}