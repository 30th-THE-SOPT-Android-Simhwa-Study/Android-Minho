package com.sopt.androidstudy.data.util

data class BaseResponse<T> (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T?
)