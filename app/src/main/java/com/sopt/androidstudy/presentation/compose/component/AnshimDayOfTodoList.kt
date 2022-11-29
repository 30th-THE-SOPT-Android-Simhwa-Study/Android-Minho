package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sopt.androidstudy.presentation.compose.model.ToDoTask

@Composable
fun AnshimDayOfTodoList(
    itemList: List<ToDoTask>
) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column() {
            LazyColumn(modifier = Modifier.padding(4.dp)) {
                items(items = itemList) { data ->
                    ToDoItem(data)
                }
            }
        }
    }
}