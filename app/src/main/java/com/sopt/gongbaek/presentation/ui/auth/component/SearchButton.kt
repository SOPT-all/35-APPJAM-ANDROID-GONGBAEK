package com.sopt.gongbaek.presentation.ui.auth.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun SearchButton(
    buttonLabel: String,
    searchResult: String,
    isSearched: Boolean = false,
    onSearchButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = buttonLabel,
            color = GongBaekTheme.colors.gray08,
            style = GongBaekTheme.typography.body2.sb14
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = GongBaekTheme.colors.gray01,
                    shape = RoundedCornerShape(6.dp)
                )
                .clickableWithoutRipple { onSearchButtonClicked() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = searchResult,
                color = if (isSearched) GongBaekTheme.colors.gray04 else GongBaekTheme.colors.gray10,
                style = GongBaekTheme.typography.body1.m16,
                modifier = Modifier
                    .padding(vertical = 14.dp)
                    .padding(start = 16.dp),
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_gray_48),
                contentDescription = null,
                tint = GongBaekTheme.colors.gray04,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSearchButton() {
    SearchButton(
        buttonLabel = "학교",
        searchResult = "학교를 검색해주세요",
        onSearchButtonClicked = {},
    )
}
