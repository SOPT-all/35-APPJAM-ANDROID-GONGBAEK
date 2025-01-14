package com.sopt.gongbaek.presentation.ui.component.topbar

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun StartTitleTopBar(
    modifier: Modifier = Modifier,
    @StringRes startTitleResId: Int? = null,
    isLeftIconIncluded: Boolean = true,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .aspectRatio(7.5f)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLeftIconIncluded) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left_48),
                contentDescription = null,
                tint = GongBaekTheme.colors.gray04,
                modifier = Modifier.clickableWithoutRipple {
                    onClick()
                }
            )
        }

        if (startTitleResId != null) {
            Text(
                text = stringResource(startTitleResId),
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.title2.m18
            )
        }
    }
}

@Preview
@Composable
private fun PreviewStartTitleTopBar() {
    Column(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
    ) {
        StartTitleTopBar()
        StartTitleTopBar(isLeftIconIncluded = false)
        StartTitleTopBar(startTitleResId = R.string.topbar_group)
        StartTitleTopBar(startTitleResId = R.string.topbar_my_group)
    }
}
