package com.sopt.androidstudy.presentation.compose.model

import androidx.annotation.DrawableRes
import com.sopt.androidstudy.R

enum class ToDoType(@DrawableRes val res: Int, val info: String) {
    HANG_OUT(R.drawable.ic_hang_out, "약속"),
    STUDY(R.drawable.ic_study, "스터디"),
    EXERCISE(R.drawable.ic_exercise, "운동"),
    MEETING(R.drawable.ic_meeting, "미팅")
}