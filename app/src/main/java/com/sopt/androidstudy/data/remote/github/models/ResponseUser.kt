package com.sopt.androidstudy.data.remote.github.models

data class ResponseUser(
    val avatar_url: String?="",
    val login: String,
    val name: String,
    val location: String
)
