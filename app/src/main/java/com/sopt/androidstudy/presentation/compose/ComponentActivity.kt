package com.sopt.androidstudy.presentation.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.androidstudy.presentation.compose.component.AnshimCalendar
import com.sopt.androidstudy.presentation.compose.component.AnshimDayOfTodoList
import com.sopt.androidstudy.presentation.compose.model.ToDoTask
import com.sopt.androidstudy.presentation.compose.model.ToDoType
import com.sopt.androidstudy.presentation.compose.theme.AndroidSturdyTheme
import timber.log.Timber
import java.time.LocalDateTime

class ComponentActivity : ComponentActivity() {
    val toDoList = listOf(
        ToDoTask(
            ToDoType.MEETING,
            "헬푸미 회의", // 하우스, 뮤멘트, 노바로 커스텀 하세요!
            LocalDateTime.of(2022, 9, 18, 23, 0)
        ),
        ToDoTask(
            ToDoType.EXERCISE,
            "새벽 산책",
            LocalDateTime.of(2022, 10, 13, 6, 0)
        ),
        ToDoTask(
            ToDoType.EXERCISE,
            "밤산책",
            LocalDateTime.of(2022, 10, 28, 6, 0)
        ),
        ToDoTask(
            ToDoType.EXERCISE,
            "새벽 산책",
            LocalDateTime.of(2022, 11, 3, 6, 0)
        ),
        ToDoTask(
            ToDoType.HANG_OUT,
            "안심이랑 찜질방가기",
            LocalDateTime.of(2022, 11, 3, 15, 0)
        ),
        ToDoTask(
            ToDoType.EXERCISE,
            "새벽 산책",
            LocalDateTime.of(2022, 11, 8, 6, 0)
        ),
        ToDoTask(
            ToDoType.EXERCISE,
            "벤치프레스 30회 5세트",
            LocalDateTime.of(2022, 11, 8, 7, 0)
        ),
        ToDoTask(
            ToDoType.STUDY,
            "멀티모듈이랑 맞짱뜨기",
            LocalDateTime.of(2022, 11, 8, 15, 0)
        ),

        ToDoTask(
            ToDoType.EXERCISE,
            "데드리프트 30회 5세트",
            LocalDateTime.of(2022, 11, 25, 7, 0)
        ),
        ToDoTask(
            ToDoType.EXERCISE,
            "벤치프레스 30회 5세트",
            LocalDateTime.of(2022, 11, 25, 8, 0)
        ),
        ToDoTask(
            ToDoType.STUDY,
            "토스 자소서 쓰기",
            LocalDateTime.of(2022, 11, 25, 16, 0)
        ),
        ToDoTask(
            ToDoType.MEETING,
            "위시보드 회의", // 하우스, 뮤멘트, 노바로 커스텀 하세요!
            LocalDateTime.of(2022, 11, 25, 20, 0)
        ),
        ToDoTask(
            ToDoType.STUDY,
            "심화스터디",
            LocalDateTime.of(2022, 11, 25, 22, 0)
        ),
        ToDoTask(
            ToDoType.EXERCISE,
            "풋살하기",
            LocalDateTime.of(2022, 12, 8, 15, 0)
        ),
        ToDoTask(
            ToDoType.STUDY,
            "코테 뿌시기",
            LocalDateTime.of(2022, 12, 8, 20, 0)
        ),
        ToDoTask(
            ToDoType.HANG_OUT,
            "제주도 여행",
            LocalDateTime.of(2022, 12, 18, 15, 0)
        ),

        ToDoTask(
            ToDoType.STUDY,
            "밀린 강의 청산",
            LocalDateTime.of(2022, 12, 27, 20, 0)
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidSturdyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val selectDate = remember {
                        mutableStateOf<Int?>(null)
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
                            Text(
                                text = "이전",
                                fontSize = 20.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = Color.Gray,
                                        shape = RectangleShape
                                    )
                                    .padding(10.dp)
                                    .clickable {
                                        selectDate.value = null
                                        currentMonth.value = currentMonth.value.dec()
                                    }
                            )
                            Text(
                                text = currentMonth.value.toString(),
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                            Text(
                                text = "다음",
                                fontSize = 20.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = Color.Gray,
                                        shape = RectangleShape
                                    )
                                    .padding(10.dp)
                                    .clickable {
                                        selectDate.value = null
currentMonth.value = currentMonth.value.inc().takeIf { it <= 12 } ?: currentMonth.value
                                    }
                            )
                        }
AnshimCalendar(
                            toDoTaskList = toDoList.filter { it.time.month.value == currentMonth.value }
                                .map { it.time.dayOfYear },
                            currentMonth = currentMonth.value,
                            selectValue = selectDate.value,
                            onClick = { selectDate.value = it?.dayOfYear }
                        )
                        if (selectDate.value != null) {
                            Timber.e(selectDate.value.toString())
                            AnshimDayOfTodoList(
                                toDoList.filter { it.time.dayOfYear == selectDate.value }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidSturdyTheme {
    }
}