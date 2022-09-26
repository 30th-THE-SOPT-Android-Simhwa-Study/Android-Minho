package com.sopt.androidstudy.domain.usecase

import com.sopt.androidstudy.domain.repository.ChattingRepository
import com.sopt.androidstudy.domain.util.ApiResult
import com.sopt.androidstudy.presentation.mapper.chatting.ChattingListASCMapper
import com.sopt.androidstudy.presentation.model.chating.Chat
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class WhenEnterChattingRoomImpl @Inject constructor(
    private val repository: ChattingRepository,
    private val mapper: ChattingListASCMapper
) : WhenEnterChattingRoom {
    override fun getAllChattingList(roomId: String): Flow<ApiResult<List<Chat>>> =
        repository.getChattingList(roomId).onStart {
            ApiResult.Loading(null)
        }.catch {
            ApiResult.Failure(null)
        }.map {
            ApiResult.Success(mapper.map(it))
        }
}