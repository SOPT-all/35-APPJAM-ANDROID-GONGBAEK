package com.sopt.gongbaek.presentation.ui.auth.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun EmptySearchResultView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height((LocalConfiguration.current.screenHeightDp * 0.256f).dp))
        Text(
            text = "검색 결과가 없습니다.\n검색어가 올바른지 확인하고 다시 검색해주세요.",
            style = GongBaekTheme.typography.caption2.m12,
            color = GongBaekTheme.colors.gray06,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewEmptySearchResultView() {
    EmptySearchResultView()
}
