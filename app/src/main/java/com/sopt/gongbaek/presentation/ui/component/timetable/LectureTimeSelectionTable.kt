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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.domain.type.DayOfWeekType
import com.sopt.gongbaek.presentation.ui.component.timetable.item.DayHeaderItem
import com.sopt.gongbaek.presentation.ui.component.timetable.item.TimeLabelsItem
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.presentation.util.timetable.updateSelectedTimeSlots
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun LectureTimeSelectionTable(
    selectedTimeSlotsByDay: Map<String, List<Int>>,
    onTimeSlotSelectionChange: (String, List<Int>) -> Unit,
    timeSlotLabels: List<String> = listOf("9", "10", "11", "12", "13", "14", "15", "16", "17"),
    daysOfWeek: List<String> = DayOfWeekType.getWeekDaysWithoutSuffix()
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier
            .height(screenHeight * 0.677f)
            .border(
                width = 1.dp,
                color = GongBaekTheme.colors.gray02,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TimeLabelsItem(
            timeSlotLabels = timeSlotLabels,
            modifier = Modifier.width(screenWidth * 0.07f)
        )

        daysOfWeek.forEach { day ->
            val selectedTimeSlots = selectedTimeSlotsByDay[day] ?: emptyList()
            LectureDayTimeSlotColumn(
                dayName = day,
                modifier = Modifier.weight(1f),
                selectedTimeSlots = selectedTimeSlots,
                onTimeSlotClick = { index ->
                    val updatedSlots = updateSelectedTimeSlots(selectedTimeSlots, index)
                    onTimeSlotSelectionChange(day, updatedSlots)
                }
            )
        }
    }
}

@Composable
private fun LectureDayTimeSlotColumn(
    dayName: String,
    selectedTimeSlots: List<Int>,
    onTimeSlotClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        DayHeaderItem(
            label = dayName,
            isSelected = false
        )

        LectureDayTimeSelector(
            dayName = dayName,
            selectedTimeSlots = selectedTimeSlots,
            onTimeSlotClick = onTimeSlotClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun LectureDayTimeSelector(
    dayName: String,
    selectedTimeSlots: List<Int>,
    onTimeSlotClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    timeSlotCount: Int = 18
) {
    repeat(timeSlotCount) { index ->
        val isSelected = selectedTimeSlots.contains(index)
        val bottomCornerShape = RoundedCornerShape(
            bottomEnd = if (dayName == "금" && index == timeSlotCount - 1) 8.dp else 0.dp
        )

        Box(
            modifier = modifier
                .fillMaxWidth()
                .clickableWithoutRipple { onTimeSlotClick(index) }
                .border(
                    width = if (isSelected) 0.dp else 0.5.dp,
                    color = if (isSelected) GongBaekTheme.colors.subOrange else GongBaekTheme.colors.gray02,
                    shape = bottomCornerShape
                )
                .background(
                    color = if (isSelected) GongBaekTheme.colors.subOrange else GongBaekTheme.colors.white,
                    shape = bottomCornerShape
                )
        )
    }
}

@Preview
@Composable
private fun PreviewSelectableLectureTimeTable() {
    val timeSlotLabels = listOf("9", "10", "11", "12", "13", "14", "15", "16", "17")
    val selectedTimeSlotsByDay = remember { mutableStateOf(mapOf<String, List<Int>>()) }

    Column(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        LectureTimeSelectionTable(
            timeSlotLabels = timeSlotLabels,
            selectedTimeSlotsByDay = selectedTimeSlotsByDay.value,
            onTimeSlotSelectionChange = { day, updatedIndices ->
                selectedTimeSlotsByDay.value += (day to updatedIndices)
            }
        )
    }
}
