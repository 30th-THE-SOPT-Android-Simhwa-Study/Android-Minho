package com.sopt.androidstudy.presentation.state


sealed class UiState<out T>{
    object Idle : UiState<Nothing>()
    data class Loading(val isLoading: Boolean) : UiState<Nothing>()
    data class Success<T>(val datas: T) : UiState<T>()
    data class Empty(val isEmpty: Boolean) : UiState<Nothing>()
    data class Failure(val throwable: String) : UiState<Nothing>()
}
