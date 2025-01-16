package com.sopt.gongbaek.presentation.ui.component.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.ui.component.timetable.item.DayHeaderItem
import com.sopt.gongbaek.presentation.ui.component.timetable.item.TimeItem
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GongBaekTimeTable(
    classTime: Map<String, List<Int>>,
    timeLabels: List<String> = listOf("9", "10", "11", "12", "13", "14", "15", "16", "17"),
    weekdays: List<String> = listOf("월", "화", "수", "목", "금"),
) {
    Row(
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.67f)
            .border(
                width = 1.dp,
                color = GongBaekTheme.colors.gray02,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TimeItem(
            timeLabels = timeLabels,
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp * 0.07f)
        )
        weekdays.forEach { day ->
            TimeSlot(
                dayLabel = day,
                classTime = classTime[day] ?: emptyList(),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun TimeSlot(
    dayLabel: String,
    classTime: List<Int>,
    modifier: Modifier = Modifier,
    timeSlotCount: Int = 18
) {
    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        DayHeaderItem(
            dayLabel = dayLabel,
            isSelected = false
        )

        repeat(timeSlotCount) { index ->
            val isClassTime = classTime.contains(index)
            val bottomCornerShape = RoundedCornerShape(
                bottomEnd = if (dayLabel == "금" && index == timeSlotCount - 1) 8.dp else 0.dp
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .border(
                        width = 0.5.dp,
                        color = if (isClassTime) GongBaekTheme.colors.gray02 else GongBaekTheme.colors.subOrange,
                        shape = bottomCornerShape
                    )
                    .background(
                        color = if (isClassTime) GongBaekTheme.colors.white else GongBaekTheme.colors.subOrange,
                        shape = bottomCornerShape
                    )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewGongBaekTimeTable() {
    val classTime = mapOf(
        "월" to listOf(0, 1, 2, 3, 4, 5, 6),
        "화" to listOf(7, 8, 9, 10),
        "수" to listOf(0, 1, 2, 3, 4, 5, 6),
        "목" to listOf(3, 4, 5, 6, 7),
        "금" to listOf(0, 1, 2, 3, 9, 10, 11, 12)
    )

    GongBaekTimeTable(
        classTime = classTime
    )
}
