package com.sopt.androidstudy.presentation.compose.model

import java.time.LocalDateTime

data class ToDoTask(val type: ToDoType, val content: String, val time: LocalDateTime)