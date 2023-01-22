package com.sopt.androidstudy.presentation.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.androidstudy.presentation.compose.component.AnshimCalendarScreen
import com.sopt.androidstudy.presentation.compose.model.ToDoTask
import com.sopt.androidstudy.presentation.compose.model.ToDoType
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnshimCalendarScreen(
        toDoList = MutableStateFlow(
            listOf(
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
        )
    )
}