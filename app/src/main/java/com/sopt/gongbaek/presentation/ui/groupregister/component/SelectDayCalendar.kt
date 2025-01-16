package com.sopt.gongbaek.presentation.ui.groupregister.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.util.extension.calculateDateForCell
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun SelectDayCalendar(
    modifier: Modifier = Modifier
) {
    val currentDate = LocalDate.now()

    var selectedDate by remember { mutableStateOf(currentDate) }
    var visibleYearAndMonth by remember { mutableStateOf(currentDate) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalendarHeader(
            visibleYear = visibleYearAndMonth.year,
            visibleMonth = visibleYearAndMonth.monthValue,
            onPreviousMonth = { visibleYearAndMonth = visibleYearAndMonth.minusMonths(1) },
            onNextMonth = { visibleYearAndMonth = visibleYearAndMonth.plusMonths(1) }
        )
        Spacer(modifier = Modifier.height(24.dp))

        CalendarGrid(
            selectedDate = selectedDate,
            visibleMonth = visibleYearAndMonth,
            onDateSelected = { date ->
                if (!date.isBefore(currentDate)) {
                    selectedDate = date
                }
            }
        )
    }
}

@Composable
fun CalendarHeader(
    visibleYear: Int,
    visibleMonth: Int,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left_32),
            contentDescription = null,
            modifier = Modifier
                .clickableWithoutRipple { onPreviousMonth() },
            tint = GongBaekTheme.colors.gray04
        )

        Text(
            text = stringResource(R.string.select_day_calendar_year_month, visibleYear, visibleMonth),
            color = GongBaekTheme.colors.gray09,
            style = GongBaekTheme.typography.title2.sb18,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right_32),
            contentDescription = null,
            modifier = Modifier
                .clickableWithoutRipple { onNextMonth() },
            tint = GongBaekTheme.colors.gray04
        )
    }
}

@Composable
fun CalendarGrid(
    selectedDate: LocalDate,
    visibleMonth: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val firstDayOfMonth = visibleMonth.withDayOfMonth(1)
    val daysInMonth = YearMonth.of(visibleMonth.year, visibleMonth.monthValue).lengthOfMonth()
    val startDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
    val totalCells = (startDayOfWeek + daysInMonth + 6) / 7 * 7
    val today = LocalDate.now()

    val previousMonth = visibleMonth.minusMonths(1)
    val daysInPreviousMonth = YearMonth.of(previousMonth.year, previousMonth.monthValue).lengthOfMonth()

    Column(
        modifier = modifier
            .aspectRatio(328f / 288f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf(
                R.string.select_day_calendar_sun,
                R.string.select_day_calendar_mon,
                R.string.select_day_calendar_tue,
                R.string.select_day_calendar_wed,
                R.string.select_day_calendar_thu,
                R.string.select_day_calendar_fri,
                R.string.select_day_calendar_sat
            ).forEach { day ->
                Box(
                    modifier = Modifier
                        .size(LocalConfiguration.current.screenHeightDp.dp * 0.036f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(day),
                        color = GongBaekTheme.colors.gray06,
                        style = GongBaekTheme.typography.body1.sb16,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        for (row in 0 until totalCells / 7) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (col in 0 until 7) {
                    val cellIndex = row * 7 + col

                    val date = calculateDateForCell(
                        cellIndex = cellIndex,
                        startDayOfWeek = startDayOfWeek,
                        firstDayOfMonth = firstDayOfMonth,
                        daysInPreviousMonth = daysInPreviousMonth,
                        selectedMonth = visibleMonth
                    )

                    val isToday = date == today
                    val isSelected = date == selectedDate
                    val isWeekend = date.dayOfWeek.value in listOf(6, 7)
                    val isOtherMonth = date.monthValue != visibleMonth.monthValue
                    val isBefore = date.isBefore(LocalDate.now())

                    Box(
                        modifier = Modifier
                            .size(LocalConfiguration.current.screenHeightDp.dp * 0.036f)
                            .background(
                                color = if (isSelected) GongBaekTheme.colors.mainOrange else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickableWithoutRipple {
                                if (!isOtherMonth && !isWeekend) {
                                    onDateSelected(date)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            color = when {
                                isSelected -> GongBaekTheme.colors.white
                                isToday -> GongBaekTheme.colors.mainOrange
                                isOtherMonth -> GongBaekTheme.colors.gray04
                                isWeekend -> GongBaekTheme.colors.gray04
                                isBefore -> GongBaekTheme.colors.gray04
                                else -> Color.Black
                            },
                            style = GongBaekTheme.typography.body1.sb16
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSelectDayCalendar() {
    Column(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
            .padding(20.dp)
            .fillMaxSize()
    ) {
        SelectDayCalendar()
    }
}
