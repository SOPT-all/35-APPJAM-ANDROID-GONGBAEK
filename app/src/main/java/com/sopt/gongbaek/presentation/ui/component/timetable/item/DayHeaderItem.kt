package com.sopt.gongbaek.presentation.ui.component.timetable.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun DayHeaderItem(
    dayLabel: String,
    isSelected: Boolean,
) {
    val topCornerShape = RoundedCornerShape(topEnd = if (dayLabel == "금") 8.dp else 0.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((LocalConfiguration.current.screenHeightDp * 0.03f).dp)
            .border(
                width = 0.5.dp,
                color = GongBaekTheme.colors.gray02,
                shape = topCornerShape
            )
            .background(
                color = if (isSelected) GongBaekTheme.colors.gray09 else GongBaekTheme.colors.white,
                shape = topCornerShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = dayLabel,
            color = if (isSelected) GongBaekTheme.colors.white else GongBaekTheme.colors.gray06,
            style = GongBaekTheme.typography.caption2.r12
        )
    }
}

@Preview
@Composable
private fun PreviewDayHeaderItem() {
    DayHeaderItem(
        dayLabel = "월",
        isSelected = false
    )
}

@Preview
@Composable
private fun PreviewDayHeaderItem2() {
    DayHeaderItem(
        dayLabel = "월",
        isSelected = true
    )
}
