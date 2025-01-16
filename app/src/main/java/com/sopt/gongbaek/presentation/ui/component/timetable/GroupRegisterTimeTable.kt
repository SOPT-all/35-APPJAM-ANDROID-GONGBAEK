package com.sopt.gongbaek.presentation.ui.component.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.timetable.item.DayHeaderItem
import com.sopt.gongbaek.presentation.ui.component.timetable.item.TimeLabelsItem
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.presentation.util.extension.roundedBackgroundWithBorder
import com.sopt.gongbaek.presentation.util.timetable.groupConsecutiveIndices
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupRegisterTimeTable(
    selectedTimeSlotsByDay: Map<String, List<Int>>,
    selectedDay: String = "",
    onTimeSlotSelectionChange: (String, List<Int>) -> Unit,
    classTime: Map<String, List<Int>> = emptyMap(),
    timeSlotLabels: List<String> = listOf("9", "10", "11", "12", "13", "14", "15", "16", "17"),
    daysOfWeek: List<String> = listOf("월", "화", "수", "목", "금")
) {
    var resetState by remember { mutableStateOf(false) }

    val timeLabels = List(19) { index -> "${9 + index / 2}:${if (index % 2 == 0) "00" else "30"}" }
    val selectedTimeSlots = selectedTimeSlotsByDay[selectedDay] ?: emptyList()

    SelectedGroupRegisterTime(
        timeLabels = timeLabels,
        selectedTimeSlots = selectedTimeSlots
    )

    HeaderSection(
        onReset = {
            resetState = true
            daysOfWeek.forEach { day ->
                onTimeSlotSelectionChange(day, emptyList())
            }
        }
    )

    TimeTableSection(
        timeSlotLabels = timeSlotLabels,
        daysOfWeek = daysOfWeek,
        selectedDay = selectedDay,
        selectedTimeSlotsByDay = selectedTimeSlotsByDay,
        classTime = classTime,
        resetState = resetState,
        onTimeSlotSelectionChange = onTimeSlotSelectionChange
    )

    resetState = false
}

@Composable
private fun SelectedGroupRegisterTime(
    selectedTimeSlots: List<Int>,
    timeLabels: List<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .width((LocalConfiguration.current.screenWidthDp * 0.38f).dp)
                .height((LocalConfiguration.current.screenHeightDp * 0.05f).dp)
                .roundedBackgroundWithBorder(
                    cornerRadius = 8.dp,
                    backgroundColor = Color.Transparent,
                    borderColor = GongBaekTheme.colors.gray02,
                    borderWidth = 1.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = selectedTimeSlots.minOrNull()?.let { timeLabels[it] } ?: "00:00",
                style = GongBaekTheme.typography.title2.b18,
                color = GongBaekTheme.colors.gray09
            )
        }

        HorizontalDivider(
            color = GongBaekTheme.colors.gray04,
            thickness = 2.dp,
            modifier = Modifier
                .width((LocalConfiguration.current.screenWidthDp * 0.04f).dp)
        )

        Box(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .width((LocalConfiguration.current.screenWidthDp * 0.38f).dp)
                .height((LocalConfiguration.current.screenHeightDp * 0.05f).dp)
                .roundedBackgroundWithBorder(
                    cornerRadius = 8.dp,
                    backgroundColor = Color.Transparent,
                    borderColor = GongBaekTheme.colors.gray02,
                    borderWidth = 1.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = selectedTimeSlots.maxOrNull()?.let { timeLabels[it + 1] } ?: "00:00",
                style = GongBaekTheme.typography.title2.b18,
                color = GongBaekTheme.colors.gray09
            )
        }
    }
}

@Composable
private fun HeaderSection(
    onReset: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "나의 시간표",
            color = GongBaekTheme.colors.gray08,
            style = GongBaekTheme.typography.body1.b16
        )
        Row(
            modifier = Modifier.clickableWithoutRipple { onReset() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            Text(
                text = "다시 선택",
                color = GongBaekTheme.colors.mainOrange,
                style = GongBaekTheme.typography.caption2.m12
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_option_reset_18),
                contentDescription = "다시 선택",
                tint = GongBaekTheme.colors.mainOrange
            )
        }
    }
}

@Composable
private fun TimeTableSection(
    timeSlotLabels: List<String>,
    daysOfWeek: List<String>,
    selectedDay: String,
    selectedTimeSlotsByDay: Map<String, List<Int>>,
    classTime: Map<String, List<Int>>,
    resetState: Boolean,
    onTimeSlotSelectionChange: (String, List<Int>) -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier
            .height(screenHeight * 0.67f)
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
            GroupRegisterDayTimeSlotColumn(
                dayName = day,
                selectedDay = selectedDay,
                modifier = Modifier.weight(1f),
                selectedTimeSlots = selectedTimeSlotsByDay[day] ?: emptyList(),
                classTime = classTime,
                resetSelection = resetState,
                onTimeSlotClick = { updatedIndices ->
                    onTimeSlotSelectionChange(day, updatedIndices)
                }
            )
        }
    }
}

@Composable
fun GroupRegisterDayTimeSlotColumn(
    dayName: String,
    selectedTimeSlots: List<Int>,
    modifier: Modifier = Modifier,
    selectedDay: String = "",
    classTime: Map<String, List<Int>> = emptyMap(),
    onTimeSlotClick: (List<Int>) -> Unit,
    timeSlotCount: Int = 18,
    resetSelection: Boolean = false
) {
    var selectionStart by remember { mutableStateOf<Int?>(null) }

    if (resetSelection) {
        selectionStart = null
    }

    val validSelectionIndices = (0 until timeSlotCount).filter { it !in classTime[dayName].orEmpty() }
    val validGroups = groupConsecutiveIndices(validSelectionIndices)

    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        DayHeaderItem(
            label = dayName,
            isSelected = selectedDay == dayName
        )

        repeat(timeSlotCount) { index ->
            val isSelected = selectedTimeSlots.contains(index)
            val isDisabled = selectedDay != dayName || classTime[dayName]?.contains(index) == true
            val isClassTime = classTime[dayName]?.contains(index) == true
            val bottomCornerShape = RoundedCornerShape(
                bottomEnd = if (dayName == "금" && index == timeSlotCount - 1) 8.dp else 0.dp
            )
            val disabledColor = if (isClassTime) GongBaekTheme.colors.gray02 else GongBaekTheme.colors.gray01

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickableWithoutRipple(enabled = !isDisabled) {
                        fun startSelection(index: Int, validGroups: List<List<Int>>) {
                            val currentGroup = validGroups.find { it.contains(index) }
                            if (currentGroup != null) {
                                selectionStart = index
                                onTimeSlotClick(listOf(index))
                            }
                        }

                        fun expandSelection(index: Int, validGroups: List<List<Int>>, startIndex: Int) {
                            val startGroup = validGroups.find { it.contains(startIndex) }
                            val currentGroup = validGroups.find { it.contains(index) }

                            if (startGroup == currentGroup && currentGroup != null) {
                                val range = if (startIndex < index) startIndex..index else index..startIndex
                                val validIndices = range.filter { it in currentGroup }
                                val updatedSelection = (selectedTimeSlots + validIndices)
                                    .distinct()
                                    .sorted()
                                onTimeSlotClick(updatedSelection)
                            } else {
                                startSelection(index, validGroups)
                            }
                        }

                        if (selectionStart == null) {
                            startSelection(index, validGroups)
                        } else {
                            expandSelection(index, validGroups, selectionStart!!)
                        }
                    }
                    .border(
                        width = if (isSelected) 0.dp else 0.5.dp,
                        color = if (isSelected) GongBaekTheme.colors.mainOrange else GongBaekTheme.colors.gray02,
                        shape = bottomCornerShape
                    )
                    .background(
                        color = when {
                            isSelected -> GongBaekTheme.colors.mainOrange
                            isDisabled -> disabledColor
                            else -> GongBaekTheme.colors.white
                        },
                        shape = bottomCornerShape
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewGroupRegisterTimeTable() {
    val timeSlotLabels = listOf("9", "10", "11", "12", "13", "14", "15", "16", "17")
    val selectedTimeSlotsByDay = remember { mutableStateOf(mapOf<String, List<Int>>()) }
    val selectedDay = "금"

    val classTime = mapOf(
        "월" to listOf(0, 1, 2, 3, 4, 5, 6),
        "화" to listOf(7, 8, 9, 10),
        "수" to listOf(0, 1, 2, 3, 4, 5, 6),
        "목" to listOf(3, 4, 5, 6, 7),
        "금" to listOf(0, 1, 2, 3, 9, 10, 11, 12)
    )

    Column(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        GroupRegisterTimeTable(
            timeSlotLabels = timeSlotLabels,
            selectedDay = selectedDay,
            selectedTimeSlotsByDay = selectedTimeSlotsByDay.value,
            classTime = classTime,
            onTimeSlotSelectionChange = { day, updatedIndices ->
                selectedTimeSlotsByDay.value = selectedTimeSlotsByDay.value.toMutableMap().apply {
                    this[day] = updatedIndices
                }
            }
        )
    }
}
