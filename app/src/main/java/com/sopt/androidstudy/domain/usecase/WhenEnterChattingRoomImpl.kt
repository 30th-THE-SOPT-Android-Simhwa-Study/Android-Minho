package com.sopt.androidstudy.domain.usecase

import com.sopt.androidstudy.data.mappers.ChattingMapper
import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.domain.repository.ChattingRepository
import com.sopt.androidstudy.domain.util.ApiResult
import com.sopt.androidstudy.presentation.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

class WhenEnterChattingRoomImpl @Inject constructor(
    private val repository: ChattingRepository,
    private val mapper: ChattingMapper
) : WhenEnterChattingRoom {
    override suspend fun getAllChattingList(roomId: String): Flow<List<ChatEntity>?> = flow {
        runCatching {
            repository.getChattingList(roomId)
        }.onFailure { throwable ->
            throw throwable
        }.onSuccess {
            when (it) {
                is ApiResult.Success -> {
                    emit(it.datas)
                }
                is ApiResult.Failure -> {
                    Timber.e(it.throwable)
                }
            }
        }
    }
}