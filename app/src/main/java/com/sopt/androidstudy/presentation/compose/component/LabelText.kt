package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.androidstudy.R
import com.sopt.androidstudy.presentation.compose.model.ToDoType
import com.sopt.androidstudy.presentation.compose.theme.Black500
import com.sopt.androidstudy.presentation.compose.theme.Gmarket
import com.sopt.androidstudy.presentation.compose.theme.Gray500
import java.time.LocalDateTime

@Composable
fun LabelText(
    typeOfList: Set<ToDoType>?,
    date: LocalDateTime?,
    isSelected: Boolean,
    today: Boolean,
    onClickLabel: (LocalDateTime?) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .size(36.dp)
            .clickable { onClickLabel(date) }) {
        Text(
            text = date?.dayOfMonth?.toString() ?: "",
            fontSize = 14.sp,
            color = Black500,
            textAlign = TextAlign.Center,
            fontFamily = Gmarket,
            modifier = if (isSelected) {
                Modifier
                    .weight(1F, false)
                    .clip(CircleShape)
                    .background(Gray500)
            } else {
                Modifier
                    .weight(1F, false)
            },
            fontWeight = if (today) {
                FontWeight.Bold
            } else {
                FontWeight.Medium
            }
        )
        Row() {
            typeOfList?.forEach { type ->
                Icon(
                    painter = painterResource(id = R.drawable.ic_dot),
                    contentDescription = null,
                    modifier = Modifier
                        .size(10.dp),
                    tint = type.color
                )
            }
        }
    }
}