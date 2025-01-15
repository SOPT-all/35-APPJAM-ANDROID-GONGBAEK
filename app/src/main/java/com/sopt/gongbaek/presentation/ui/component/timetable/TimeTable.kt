package com.sopt.gongbaek.presentation.ui.component.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.ui.component.timetable.item.TimeColumn
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun TimeTable(
    timeLabels: List<String>,
    selectedIndicesByDay: Map<String, List<Int>>,
    onSelectionChange: (String, List<Int>) -> Unit
) {
    val weekdays = listOf("월", "화", "수", "목", "금")

    Row(
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.67f)
            .border(
                1.dp,
                GongBaekTheme.colors.gray02,
                RoundedCornerShape(8.dp)
            )
    ) {
        TimeColumn(
            timeLabels = timeLabels,
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp * 0.07f)
        )

        weekdays.forEach { day ->
            DayColumn(
                day = day,
                modifier = Modifier.weight(1f),
                selectedIndices = selectedIndicesByDay[day] ?: emptyList(),
                onSelectionChange = { updatedIndices ->
                    onSelectionChange(day, updatedIndices)
                }
            )
        }
    }
}

@Composable
fun DayColumn(
    day: String,
    modifier: Modifier = Modifier,
    selectedIndices: List<Int>,
    onSelectionChange: (List<Int>) -> Unit
) {
    val headerShape = if (day == "금") RoundedCornerShape(topEnd = 8.dp) else RoundedCornerShape(topEnd = 0.dp)

    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.03f)
                .border(
                    width = 0.5.dp,
                    color = GongBaekTheme.colors.gray02,
                    shape = headerShape
                )
                .background(
                    color = GongBaekTheme.colors.white,
                    shape = headerShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day,
                color = GongBaekTheme.colors.gray06,
                style = GongBaekTheme.typography.caption2.r12
            )
        }

        repeat(18) { index ->
            val isSelected = selectedIndices.contains(index)
            val cellShape = if (day == "금" && index == 17) RoundedCornerShape(bottomEnd = 8.dp) else RoundedCornerShape(bottomEnd = 0.dp)

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickableWithoutRipple {
                        val updatedIndices = if (isSelected) {
                            selectedIndices - index
                        } else {
                            selectedIndices + index
                        }
                        onSelectionChange(updatedIndices)
                    }
                    .border(
                        width = if (isSelected) 0.dp else 0.5.dp,
                        color = if (isSelected) GongBaekTheme.colors.subOrange else GongBaekTheme.colors.gray02,
                        shape = cellShape
                    )
                    .background(
                        color = if (isSelected) GongBaekTheme.colors.subOrange else GongBaekTheme.colors.white,
                        shape = cellShape
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTimeTable() {
    val timeLabels = listOf("9", "10", "11", "12", "13", "14", "15", "16", "17")
    val selectedIndicesByDay = remember {
        mutableStateOf(
            listOf("월", "화", "수", "목", "금").associateWith { emptyList<Int>() }
        )
    }

    Column(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        TimeTable(
            timeLabels = timeLabels,
            selectedIndicesByDay = selectedIndicesByDay.value,
            onSelectionChange = { day, updatedIndices ->
                selectedIndicesByDay.value = selectedIndicesByDay.value.toMutableMap().apply {
                    this[day] = updatedIndices
                }
            }
        )
    }
}
