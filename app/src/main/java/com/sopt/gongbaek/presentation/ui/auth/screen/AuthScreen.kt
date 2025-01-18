package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun AuthRoute(
    navigateHome: () -> Unit
) {
    AuthScreen(
        navigateHome = navigateHome
    )
}

@Composable
fun AuthScreen(
    navigateHome: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "온보딩 화면" +
                "\n홈으로 이동",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(color = GongBaekTheme.colors.gray04)
                .clickableWithoutRipple(onClick = navigateHome)
                .padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAuthScreen() {
    GONGBAEKTheme {
        AuthScreen(
            navigateHome = {}
        )
    }
}
