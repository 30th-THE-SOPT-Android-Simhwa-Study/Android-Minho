package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.androidstudy.R
import java.time.LocalDateTime

@Composable
fun LabelText(
    toDoTask: Boolean,
    date: LocalDateTime?,
    isSelected: Boolean,
    onClickLabel: (LocalDateTime?) -> Unit
) {
    Column(
        modifier = Modifier
            .border(width = 1.dp, color = Color.Gray, shape = RectangleShape)
            .padding(10.dp)
            .size(35.dp)
            .clickable { onClickLabel(date) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = date?.dayOfMonth?.toString() ?: "",
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Light
        )
        if (toDoTask) {
            Image(
                painter = painterResource(id = R.drawable.ic_dot), contentDescription = null,
                modifier = Modifier.size(10.dp)
            )
        }
    }
}