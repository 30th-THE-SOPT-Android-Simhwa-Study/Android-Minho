package com.sopt.androidstudy.presentation.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.sopt.androidstudy.presentation.compose.component.AnshimCalendarScreen

class ComponentActivity : ComponentActivity() {
    val composeViewModel: ComposeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        composeViewModel.getToDoList()
        setContent {
            AnshimCalendarScreen(toDoList = composeViewModel.toDoList)
        }
    }
}