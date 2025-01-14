package com.sopt.gongbaek.presentation.ui.component.topbar

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
fun CenterTitleTopBar(
    @StringRes centerTitleResId: Int,
    modifier: Modifier = Modifier,
    isRightIconIncluded: Boolean? = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .aspectRatio(7.5f)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(centerTitleResId),
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.title2.m18
            )
        }

        if (isRightIconIncluded == true) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_x_48),
                    contentDescription = null,
                    tint = GongBaekTheme.colors.gray04,
                    modifier = Modifier.clickableWithoutRipple {
                        onClick()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCenterTitleTopBar() {
    Column(
        modifier = Modifier.background(color = GongBaekTheme.colors.white)
    ) {
        CenterTitleTopBar(centerTitleResId = R.string.topbar_search, isRightIconIncluded = true)
        CenterTitleTopBar(centerTitleResId = R.string.topbar_group)
        CenterTitleTopBar(centerTitleResId = R.string.topbar_my_group)
    }
}