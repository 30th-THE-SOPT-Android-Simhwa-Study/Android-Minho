package com.sopt.androidstudy.presentation.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.androidstudy.presentation.compose.component.LabelText
import com.sopt.androidstudy.presentation.compose.component.ToDoList
import com.sopt.androidstudy.presentation.compose.model.ToDoTask
import com.sopt.androidstudy.presentation.compose.model.ToDoType
import com.sopt.androidstudy.presentation.compose.theme.AndroidSturdyTheme

class ComponentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSturdyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Calendar(
                        "Android",
                        listOf(
                            ToDoTask(ToDoType.HANG_OUT, "Hi Hello", "오전 6시"),
                            ToDoTask(ToDoType.STUDY, "Hi Hello", "오전 6시"),
                            ToDoTask(ToDoType.EXERCISE, "Hi Hello", "오전 6시"),
                            ToDoTask(ToDoType.MEETING, "Hi Hello", "오전 6시")
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun Calendar(
    name: String, itemList: List<ToDoTask>
) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column() {
            LazyRow(modifier = Modifier.padding(4.dp)) {
                itemsIndexed(items = itemList) { index, data ->
                    LabelText(data, index)
                }
            }
            LazyColumn(modifier = Modifier.padding(4.dp)) {
                items(items = itemList) { data ->
                    ToDoList(data)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidSturdyTheme {
        Calendar(
            "Android", listOf(
                ToDoTask(ToDoType.HANG_OUT, "Hi Hello", "오전 6시"),
                ToDoTask(ToDoType.STUDY, "Hi Hello", "오전 6시"),
                ToDoTask(ToDoType.EXERCISE, "Hi Hello", "오전 6시"),
                ToDoTask(ToDoType.MEETING, "Hi Hello", "오전 6시")
            )
        )
    }
}