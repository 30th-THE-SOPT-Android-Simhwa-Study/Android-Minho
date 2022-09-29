package com.sopt.androidstudy.domain.util

sealed class ApiResult<out T> {
    object Idle : ApiResult<Nothing>()
    data class Loading(val isLoading: Boolean) : ApiResult<Nothing>()
    data class Success<T>(val datas: T) : ApiResult<T>()
    data class Empty(val isEmpty: Boolean) : ApiResult<Nothing>()
    data class Failure(val throwable: String) : ApiResult<Nothing>()
}