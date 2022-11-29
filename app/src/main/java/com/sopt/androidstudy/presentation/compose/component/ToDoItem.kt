package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopt.androidstudy.presentation.compose.model.ToDoTask

@Composable
fun ToDoItem(toDoTask: ToDoTask) {
    Column(Modifier.padding(16.dp, 5.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = toDoTask.type.res),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
                    .weight(1F)
            ) {
                Text(text = toDoTask.type.info, color = toDoTask.type.color)
                Text(text = toDoTask.content, color = Color.Black)
                Text(
                    text = (if (toDoTask.time.hour / 12 == 1) "오후 " + toDoTask.time.hour % 12 + "시" else "오전 " + toDoTask.time.hour + "시").toString(),
                    color = Color.Black
                )
            }
        }
    }
}