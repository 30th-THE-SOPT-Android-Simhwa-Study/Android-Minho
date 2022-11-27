package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.androidstudy.R
import com.sopt.androidstudy.presentation.compose.model.ToDoTask

@Composable
fun LabelText(toDoTask: ToDoTask?, date: Int) {
    val localDotEnabled = remember { mutableStateOf(toDoTask != null) }
    Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = date.toString(), fontSize = 20.sp, color = Color.Black)
        if (localDotEnabled.value) {
            Image(painter = painterResource(id = R.drawable.ic_dot), contentDescription = null)
        }
    }

}