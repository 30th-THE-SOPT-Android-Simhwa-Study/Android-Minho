package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.androidstudy.presentation.compose.theme.DividerGray
import com.sopt.androidstudy.presentation.compose.theme.Gmarket
import com.sopt.androidstudy.presentation.compose.theme.Gray500

@Composable
fun CalendarControlButton(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Gray500,
        fontFamily = Gmarket,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = DividerGray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(5.dp)
            .clickable { onClick() }
    )
}