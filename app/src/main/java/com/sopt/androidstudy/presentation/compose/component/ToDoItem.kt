package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.androidstudy.presentation.compose.model.ToDoTask
import com.sopt.androidstudy.presentation.compose.theme.Gmarket
import com.sopt.androidstudy.presentation.compose.theme.Gray400

@Composable
fun ToDoItem(toDoTask: ToDoTask) {
    Column(
        Modifier
            .border(width = 1.dp, color = Gray400, shape = RoundedCornerShape(12F))

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Image(
                painter = painterResource(id = toDoTask.type.res),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1F)
            ) {
                Text(
                    text = toDoTask.type.info,
                    color = toDoTask.type.color,
                    fontSize = 10.sp,
                    fontFamily = Gmarket,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = toDoTask.content,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = Gmarket,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(21.dp))
                Text(
                    fontSize = 10.sp,
                    text = (if (toDoTask.time.hour / 12 == 1) "오후 " + toDoTask.time.hour % 12 + "시" else "오전 " + toDoTask.time.hour + "시").toString(),
                    color = Color.Black, fontFamily = Gmarket, fontWeight = FontWeight.Medium
                )
            }
        }
    }
}