package com.sopt.gongbaek.presentation.ui.groupregister.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
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
import com.sopt.gongbaek.presentation.util.extension.roundedBackgroundWithBorder
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupPeopleCounter(
    peopleCount: Int,
    onMinusButtonClicked: () -> Unit,
    onPlusButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GroupPeopleCounterButton(
            iconResId = R.drawable.ic_minus_main_orange_18,
            onClick = onMinusButtonClicked
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f)
                .roundedBackgroundWithBorder(
                    cornerRadius = 6.dp,
                    backgroundColor = GongBaekTheme.colors.white,
                    borderColor = GongBaekTheme.colors.gray03,
                    borderWidth = 1.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.group_place_people_count, peopleCount),
                color = GongBaekTheme.colors.gray10,
                style = GongBaekTheme.typography.title1.b20,
                modifier = Modifier.padding(vertical = 14.dp)
            )
        }
        GroupPeopleCounterButton(
            iconResId = R.drawable.ic_plus_main_orange_18,
            onClick = onPlusButtonClicked
        )
    }
}

@Composable
private fun GroupPeopleCounterButton(
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
            .clickableWithoutRipple(onClick = onClick)
            .padding(15.dp)
    )
}

@Preview
@Composable
private fun PreviewGroupPeopleCounter() {
    var peopleCount by remember { mutableIntStateOf(2) }
    Box(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        GroupPeopleCounter(
            peopleCount = peopleCount,
            onMinusButtonClicked = { peopleCount -= 1 },
            onPlusButtonClicked = { peopleCount += 1 }
        )
    }
}
