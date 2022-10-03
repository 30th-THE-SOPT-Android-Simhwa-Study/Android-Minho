package com.sopt.androidstudy.domain.util

sealed class ApiResult<out T> {
    data class Success<T>(val datas: T) : ApiResult<T>()
    data class Failure(val throwable: String) : ApiResult<Nothing>()
}