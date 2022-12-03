package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalendarControllButton(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = Color.Black,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RectangleShape
            )
            .padding(10.dp)
            .clickable { onClick() }
    )
}