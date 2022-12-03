package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.androidstudy.presentation.compose.model.ToDoTask
import com.sopt.androidstudy.presentation.compose.theme.AndroidSturdyTheme
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime

@Composable
fun AnshimCalendarScreen(toDoList: StateFlow<List<ToDoTask>>) {
    val myTodoList = toDoList.collectAsState()
    AndroidSturdyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val selectDate = remember {
                mutableStateOf(0)
            }
            val currentMonth = remember {
                mutableStateOf(LocalDateTime.now().month.value)
            }
            Column() {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                        .padding(0.dp, 12.dp, 0.dp, 20.dp)
                        .fillMaxWidth()
                ) {
                    CalendarControlButton(
                        text = "이전",
                        onClick = {
                            currentMonth.value =
                                currentMonth.value
                                    .dec()
                                    .takeIf { it >= 1 }
                                    ?: currentMonth.value
                        }
                    )
                    Text(
                        text = currentMonth.value.toString(),
                        fontSize = 20.sp,
                        color = Color.Black,
                    )
                    CalendarControlButton(
                        text = "다음",
                        onClick = {
                            currentMonth.value =
                                currentMonth.value
                                    .inc()
                                    .takeIf { it <= 12 }
                                    ?: currentMonth.value
                        }
                    )
                }
                AnshimCalendar(
                    toDoTaskList = myTodoList.value.filter { it.time.month.value == currentMonth.value },
                    currentMonth = currentMonth.value,
                    selectValue = selectDate.value
                ) { selectDate.value = it?.dayOfYear ?: 0 }
                if (selectDate.value != 0) {
                    AnshimDayOfTodoList(
                        myTodoList.value.filter { it.time.dayOfYear == selectDate.value }
                    )
                }
            }
        }
    }
}