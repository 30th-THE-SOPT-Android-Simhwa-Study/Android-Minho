package com.sopt.androidstudy.domain.usecase

import com.sopt.androidstudy.domain.entity.ChatEntity
import com.sopt.androidstudy.domain.repository.ChattingRepository
import com.sopt.androidstudy.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class WhenEnterChattingRoomImpl @Inject constructor(
    private val repository: ChattingRepository,
) : WhenEnterChattingRoom {
    override fun getAllChattingList(roomId: String): Flow<ApiResult<List<ChatEntity>>> =
        repository.getChattingList(roomId).catch {
            ApiResult.Failure(this.toString())
        }
}