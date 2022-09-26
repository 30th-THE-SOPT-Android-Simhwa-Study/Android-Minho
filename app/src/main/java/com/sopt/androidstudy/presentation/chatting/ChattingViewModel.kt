package com.sopt.androidstudy.presentation.chatting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.androidstudy.domain.usecase.WhenEnterChattingRoom
import com.sopt.androidstudy.domain.util.ApiResult
import com.sopt.androidstudy.presentation.model.chating.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(private val useCase: WhenEnterChattingRoom) :
    ViewModel() {

    val roomId = MutableStateFlow("-1")
    private val _chattingList = useCase.getAllChattingList(roomId.value)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ApiResult.Idle(null)
        )

    init {
        roomId.value = "57"
    }
}