package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.androidstudy.presentation.compose.model.MonthType
import com.sopt.androidstudy.presentation.compose.model.ToDoTask
import com.sopt.androidstudy.presentation.compose.theme.AndroidSturdyTheme
import com.sopt.androidstudy.presentation.compose.theme.Gmarket
import com.sopt.androidstudy.presentation.compose.theme.Gray500
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
                        .padding(0.dp, 16.dp, 0.dp, 17.dp)
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
                        text = MonthType.values()[currentMonth.value - 1].name + " 2022",
                        fontSize = 20.sp,
                        fontFamily = Gmarket,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1F)
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
                if (myTodoList.value.filter { it.time.dayOfYear == selectDate.value }
                        .isEmpty()) {
                    Spacer(modifier = Modifier.height(100.dp))
                    Image(
                        painter = painterResource(id = com.sopt.androidstudy.R.drawable.ic_noti),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "앗, 일정이 없어요! \n" +
                                "일정을 등록하고 알림을 받아보세요!",
                        fontSize = 14.sp,
                        fontFamily = Gmarket,
                        color = Gray500,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}