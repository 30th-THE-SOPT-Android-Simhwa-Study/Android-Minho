package com.sopt.androidstudy.presentation.compose.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.androidstudy.presentation.compose.model.ToDoTask
import com.sopt.androidstudy.presentation.compose.model.WeekendType
import com.sopt.androidstudy.presentation.compose.theme.DividerGray
import com.sopt.androidstudy.presentation.compose.theme.Gmarket
import com.sopt.androidstudy.presentation.compose.theme.Gray500
import java.time.LocalDateTime

@Composable
fun AnshimCalendar(
    toDoTaskList: List<ToDoTask>,
    currentMonth: Int,
    selectValue: Int?,
    onClick: (LocalDateTime?) -> Unit
) {
    var day = LocalDateTime.of(2022, currentMonth, 1, 0, 0, 0).withDayOfMonth(1)
    val toDay = LocalDateTime.now().dayOfYear
    val startDay =
        LocalDateTime.of(2022, currentMonth, 1, 0, 0, 0).withDayOfMonth(1).dayOfWeek.value % 7
    Row(
    ) {
        WeekendType.values().forEach {
            Text(
                text = it.name,
                fontSize = 12.sp,
                color = Gray500,
                modifier = Modifier.weight(1F),
                textAlign = TextAlign.Center,
                fontFamily = Gmarket,
                fontWeight = FontWeight.Medium
            )
        }
    }
    Spacer(modifier = Modifier.height(7.dp))
    Divider(thickness = 0.5.dp, color = DividerGray)
    Column() {
        repeat(if (startDay > 4) 6 else 5) { column ->
            if (column == 5 && day.dayOfMonth == 1) return@repeat
            Row(Modifier.fillMaxWidth()) {
                repeat(7) { row ->
                    if (column == 0 && startDay > row) {
                        LabelText(
                            typeOfList = null,
                            date = null,
                            isSelected = false, today = false
                        ) { onClick(null) }
                    } else {
                        LabelText(
                            typeOfList = toDoTaskList.filter { it.time.dayOfYear == day.dayOfYear }
                                .map { it.type }.toSet(),
                            date = day,
                            isSelected = selectValue == day.dayOfYear,
                            today = toDay == day.dayOfYear
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