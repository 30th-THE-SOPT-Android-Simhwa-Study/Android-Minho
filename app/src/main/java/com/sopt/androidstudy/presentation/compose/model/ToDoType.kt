package com.sopt.androidstudy.presentation.compose.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.sopt.androidstudy.R
import com.sopt.androidstudy.presentation.compose.theme.Blue500
import com.sopt.androidstudy.presentation.compose.theme.Green500
import com.sopt.androidstudy.presentation.compose.theme.Orange500
import com.sopt.androidstudy.presentation.compose.theme.Pink500

enum class ToDoType(@DrawableRes val res: Int, val info: String, val color: Color) {
    HANG_OUT(R.drawable.ic_hang_out, "약속", Green500),
    STUDY(R.drawable.ic_study, "스터디", Blue500),
    EXERCISE(R.drawable.ic_exercise, "운동", Pink500),
    MEETING(R.drawable.ic_meeting, "미팅", Orange500)
}