package com.sopt.androidstudy.service

data class Message(
    val roomId: Int,
    val audience: String,
    val audienceId: Int,
    val audienceProfile: String,
    val content: String,
    val createdAt: String
)