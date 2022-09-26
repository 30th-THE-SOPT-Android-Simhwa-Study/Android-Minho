package com.sopt.androidstudy.domain.usecase

import com.sopt.androidstudy.domain.repository.ChattingRepository
import com.sopt.androidstudy.domain.util.ApiResult
import com.sopt.androidstudy.presentation.mapper.chatting.ChattingListASCMapper
import com.sopt.androidstudy.presentation.model.chating.Chat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class WhenEnterChattingRoomImpl @Inject constructor(
    private val repository: ChattingRepository,
    private val mapper: ChattingListASCMapper
) : WhenEnterChattingRoom {
    override fun getAllChattingList(roomId: String): Flow<ApiResult<List<Chat>>> = flow {
        repository.getChattingList(roomId.toInt())
            .onEach { emit(ApiResult.Loading(null)) }
            .map { ApiResult.Success(mapper.map(it)) }
            .catch { ApiResult.Failure(null) }
    }.flowOn(Dispatchers.IO)
}