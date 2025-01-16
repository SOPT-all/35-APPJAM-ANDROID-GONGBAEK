package com.sopt.gongbaek.presentation.ui.component.timetable.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun TimeLabelsColumn(
    timeSlotLabels: List<String>,
    modifier: Modifier = Modifier
) {
    val commonModifier = Modifier
        .fillMaxWidth()
        .background(color = GongBaekTheme.colors.white)
        .border(
            width = 0.5.dp,
            color = GongBaekTheme.colors.gray02
        )

    Column(
        modifier = modifier
    ) {
        // 상단 Rounded Corner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.03f)
                .background(
                    color = GongBaekTheme.colors.white,
                    shape = RoundedCornerShape(topStart = 8.dp)
                )
                .border(
                    width = 0.5.dp,
                    color = GongBaekTheme.colors.gray02,
                    shape = RoundedCornerShape(topStart = 8.dp)
                )
        )

        timeSlotLabels.forEachIndexed { index, timeLabel ->
            Box(
                modifier = commonModifier.weight(1f),
                contentAlignment = Alignment.TopEnd
            ) {
                Text(
                    text = timeLabel,
                    color = GongBaekTheme.colors.gray06,
                    style = GongBaekTheme.typography.caption2.r12
                )
            }

            if (index < timeSlotLabels.size - 1) {
                Box(
                    modifier = commonModifier.weight(1f)
                )
            }
        }

        // 하단 Rounded Corner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = GongBaekTheme.colors.white,
                    shape = RoundedCornerShape(bottomStart = 8.dp)
                )
                .border(
                    width = 0.5.dp,
                    color = GongBaekTheme.colors.gray02,
                    shape = RoundedCornerShape(bottomStart = 8.dp)
                )
        )
    }
}

@Preview
@Composable
private fun PreviewTimeItem() {
    val timeLabels = listOf("9", "10", "11", "12", "13", "14", "15", "16", "17")

    Column(
        Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        TimeLabelsColumn(
            timeSlotLabels = timeLabels
        )
    }
}
