package com.sopt.gongbaek.presentation.ui.grouplist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.domain.type.DayOfWeekType
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun DayOfWeekBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onIndexSelected: (Int) -> Unit = {}
) {
    val contentLists = listOf(
        DayOfWeekType.ALL.description,
        DayOfWeekType.MON.description,
        DayOfWeekType.TUE.description,
        DayOfWeekType.WED.description,
        DayOfWeekType.THU.description,
        DayOfWeekType.FRI.description
    )

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(
                color = GongBaekTheme.colors.gray01,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 2.dp, horizontal = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        contentLists.forEachIndexed { index, content ->
            Box(
                modifier = modifier
                    .background(
                        color = if (selectedIndex == index) GongBaekTheme.colors.white else GongBaekTheme.colors.gray01,
                        shape = RoundedCornerShape(2.dp)
                    )
                    .padding(
                        horizontal = if (index == 0) 12.dp else 9.dp,
                        vertical = 8.dp
                    )
                    .clickableWithoutRipple {
//                        onIndexSelected(index)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = content,
                    color = if (selectedIndex == index) GongBaekTheme.colors.mainOrange else GongBaekTheme.colors.gray06,
                    style = GongBaekTheme.typography.caption1.sb13
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDayOfWeekBar() {
    GONGBAEKTheme {
        DayOfWeekBar()
    }
}
