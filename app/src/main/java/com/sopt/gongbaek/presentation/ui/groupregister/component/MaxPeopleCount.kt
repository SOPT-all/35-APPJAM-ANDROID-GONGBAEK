package com.sopt.gongbaek.presentation.ui.groupregister.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun MaxPeopleCount(
    peopleCount: Int,
    onPeopleCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MaxPeopleCountButton(
            iconResId = R.drawable.ic_minus_gray_18,
            onClick = {
                if (peopleCount > 2) {
                    onPeopleCountChange(peopleCount - 1)
                }
            }
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .border(
                    width = 1.dp,
                    color = GongBaekTheme.colors.gray03,
                    shape = RoundedCornerShape(6.dp)
                )
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.group_place_people_count, peopleCount),
                color = GongBaekTheme.colors.gray10,
                style = GongBaekTheme.typography.title1.b20,
                modifier = Modifier.padding(vertical = 14.dp)
            )
        }
        MaxPeopleCountButton(
            iconResId = R.drawable.ic_plus_gray_18,
            onClick = {
                if (peopleCount < 10) {
                    onPeopleCountChange(peopleCount + 1)
                }
            }
        )
    }
}

@Composable
private fun MaxPeopleCountButton(
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = ImageVector.vectorResource(iconResId),
        contentDescription = null,
        tint = GongBaekTheme.colors.mainOrange,
        modifier = modifier
            .background(
                color = GongBaekTheme.colors.subOrange,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(15.dp)
            .clickableWithoutRipple(onClick = onClick)
    )
}

@Preview
@Composable
private fun PreviewMaxPeopleCount() {
    var peopleCount by remember { mutableIntStateOf(2) }
    Box(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        MaxPeopleCount(
            peopleCount = peopleCount,
            onPeopleCountChange = { peopleCount = it }
        )
    }
}
