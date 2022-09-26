package com.sopt.androidstudy.data.repository

import android.util.Log
import com.sopt.androidstudy.data.datasources.RemoteChattingDataSource
import com.sopt.androidstudy.data.mappers.ChattingMapper
import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.domain.repository.ChattingRepository
import com.sopt.androidstudy.domain.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

class ChattingRepositoyImpl @Inject constructor(
    private val dataSource: RemoteChattingDataSource,
    private val mapper: ChattingMapper
) : ChattingRepository {
    override fun getChattingList(roomId: String): Flow<List<ChatEntity>> = flow {
        val data = dataSource.getChattingList(roomId)
        Timber.e("Data!! $data")
        if (!data.success) {
            Timber.e( "Not Success!!!")
        } else if (data.data == null) {
            Timber.e("Data Empty!!!")
        } else

            emit(mapper.map(data.data.messages))
    }.flowOn(Dispatchers.IO)
}