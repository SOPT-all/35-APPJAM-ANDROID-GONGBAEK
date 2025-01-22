package com.sopt.gongbaek.presentation.ui.component.picker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import com.sopt.gongbaek.ui.theme.defaultGongBaekColors

@Composable
fun GuideLines(
    visibleItemsCount: Int,
    itemHeightDp: Dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeightDp * visibleItemsCount)
    ) {
        val borderHeight = itemHeightDp.toPx()
        val center = size.height / 2
        drawLine(
            color = defaultGongBaekColors.gray05,
            start = Offset(0f, center - borderHeight / 2),
            end = Offset(size.width, center - borderHeight / 2),
            strokeWidth = 0.5f
        )
        drawLine(
            color = defaultGongBaekColors.gray05,
            start = Offset(0f, center + borderHeight / 2),
            end = Offset(size.width, center + borderHeight / 2),
            strokeWidth = 0.5f
        )
    }
}
