package com.sopt.androidstudy.domain.usecase

import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.domain.repository.ChattingRepository
import com.sopt.androidstudy.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class WhenEnterChattingRoomImpl @Inject constructor(
    private val repository: ChattingRepository,
) : WhenEnterChattingRoom {
    override fun getAllChattingList(roomId: String): Flow<ApiResult<List<ChatEntity>>> =
        repository.getChattingList(roomId).onStart {
            ApiResult.Loading(null)
        }.catch {
            ApiResult.Failure(null)
        }.map {
            ApiResult.Success(it)
        }
}