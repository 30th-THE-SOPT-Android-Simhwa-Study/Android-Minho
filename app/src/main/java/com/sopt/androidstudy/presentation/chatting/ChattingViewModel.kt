package com.sopt.androidstudy.presentation.chatting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.androidstudy.domain.usecase.WhenEnterChattingRoom
import com.sopt.androidstudy.domain.util.ApiResult
import com.sopt.androidstudy.presentation.mapper.chatting.ChattingListASCMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(
    private val useCase: WhenEnterChattingRoom
) :
    ViewModel() {
    val chattingList = useCase.getAllChattingList("57")
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ApiResult.Idle
        )
}