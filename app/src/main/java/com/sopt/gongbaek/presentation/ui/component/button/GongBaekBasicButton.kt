package com.sopt.gongbaek.presentation.ui.component.button

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GongBaekBasicButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    verticalPadding: Int = 16,
    enableButtonColor: Color = GongBaekTheme.colors.mainOrange,
    disableButtonColor: Color = GongBaekTheme.colors.gray03
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (enabled) enableButtonColor else disableButtonColor,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(vertical = verticalPadding.dp)
            .clickableWithoutRipple { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = GongBaekTheme.colors.white,
            style = GongBaekTheme.typography.title2.sb18
        )
    }
}

@Preview
@Composable
private fun PreviewGongBaekBasicButton() {
    GongBaekBasicButton(
        title = "확인",
        onClick = {}
    )
}

@Preview
@Composable
private fun PreviewGongBaekBasicButton2() {
    GongBaekBasicButton(
        title = "확인",
        onClick = {},
        enabled = false
    )
}

@Preview
@Composable
private fun PreviewGongBaekBasicButton3() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GongBaekBasicButton(
            title = "시간표 변경",
            onClick = {},
            modifier = Modifier.weight(1f),
            enableButtonColor = GongBaekTheme.colors.gray09
        )
        GongBaekBasicButton(
            title = "가입완료",
            onClick = {},
            modifier = Modifier.weight(2f)
        )
    }
}

@Preview
@Composable
private fun PreviewGongBaekBasicButton4() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GongBaekBasicButton(
            title = "4/4",
            onClick = {},
            modifier = Modifier.weight(1f),
            enabled = false,
            disableButtonColor = GongBaekTheme.colors.gray04
        )
        GongBaekBasicButton(
            title = "모집마감",
            onClick = {},
            modifier = Modifier.weight(2f),
            enabled = false,
            disableButtonColor = GongBaekTheme.colors.gray03
        )
    }
}
