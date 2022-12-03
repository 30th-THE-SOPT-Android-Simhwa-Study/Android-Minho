package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import java.time.LocalDateTime

@Composable
fun AnshimCalendar(
      toDoTaskList: List<Int>,
    currentMonth: Int,
    selectValue: Int?,
    onClick: (LocalDateTime?) -> Unit
) {
    var day = LocalDateTime.of(2022, currentMonth, 1, 0, 0, 0).withDayOfMonth(1)
    val startDay =
        LocalDateTime.of(2022, currentMonth, 1, 0, 0, 0).withDayOfMonth(1).dayOfWeek.value
    Column() {
        repeat(if (startDay > 4) 6 else 5) { column ->
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(7) { row ->
                    if (column == 0 && startDay > row || day.dayOfMonth > 31) {
                        LabelText(
                            toDoTask = false,
                            date = null,
                            false,
                            onClickLabel = { onClick(null) })
                    } else {
                        LabelText(
                            toDoTask = toDoTaskList.contains(day.dayOfYear),
                            date = day, isSelected = selectValue == day.dayOfYear
                        ) { day ->
                            onClick(day)
                        }
                        day = day.plusDays(1)
                    }
                }
            }
        }
    }
}