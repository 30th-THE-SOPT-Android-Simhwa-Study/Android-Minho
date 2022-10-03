package com.sopt.androidstudy.presentation.chatting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.androidstudy.domain.usecase.WhenEnterChattingRoom
import com.sopt.androidstudy.domain.util.ApiResult
import com.sopt.androidstudy.presentation.mapper.chatting.ChattingListASCMapper
import com.sopt.androidstudy.presentation.model.chating.Chat
import com.sopt.androidstudy.presentation.state.UiState
import com.sopt.androidstudy.presentation.util.collectFlowWhenStarted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(
    private val useCase: WhenEnterChattingRoom,
    private val mapper: ChattingListASCMapper,
) : ViewModel() {
    private val _chattingList = MutableStateFlow<UiState<List<Chat>>>(UiState.Idle)
    val chattingList get() = _chattingList

    init {
        viewModelScope.launch {
            useCase.getAllChattingList("57").onStart {
                _chattingList.value = UiState.Loading(true)
            }.catch { e ->
                _chattingList.value = UiState.Failure(throwable = e.message!!)
            }.collect { result ->
                _chattingList.value = UiState.Loading(isLoading = false)
                if (result == null) {
                    _chattingList.value = UiState.Empty(isEmpty = true)
                } else {
                    _chattingList.value = UiState.Success(datas = mapper.map(result))
                }
            }
        }
    }
}