package com.sopt.gongbaek.presentation.ui.grouplist.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import kotlin.math.roundToInt

@Composable
fun GongBaekToggleButton(
    checkedState: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val minBound = with(density) { 0.dp.toPx() }
    val maxBound = with(density) { (LocalConfiguration.current.screenWidthDp * 0.06f).dp.toPx() }
    val state by animateFloatAsState(
        targetValue = if (checkedState) maxBound else minBound,
        animationSpec = tween(durationMillis = 300)
    )
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(
                if (checkedState) {
                    R.string.grouplist_gongbaek_duplicate
                } else {
                    R.string.grouplist_gongbaek_all
                }
            ),
            color = GongBaekTheme.colors.gray06,
            style = GongBaekTheme.typography.caption2.r12
        )
        Spacer(Modifier.width(6.dp))

        Box(
            modifier = modifier
                .aspectRatio(42f / 24f)
                .height((LocalConfiguration.current.screenHeightDp * 0.03f).dp)
                .background(
                    color = if (checkedState) {
                        GongBaekTheme.colors.mainOrange
                    } else {
                        GongBaekTheme.colors.gray06
                    },
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(3.5.dp)
                .clickableWithoutRipple {
//                    onClick()
                }
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(state.roundToInt(), 0) }
                    .aspectRatio(1f / 1f)
                    .height((LocalConfiguration.current.screenHeightDp * 0.02f).dp)
                    .background(
                        color = GongBaekTheme.colors.white,
                        shape = CircleShape
                    )
            )
        }
    }
}
