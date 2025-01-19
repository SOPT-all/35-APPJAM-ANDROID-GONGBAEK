package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun ChangeTimeTableRoute() {
    ChangeTimeTableScreen()
}

@Composable
private fun ChangeTimeTableScreen(
    modifier: Modifier = Modifier
) {
}

@Preview
@Composable
private fun PreviewChangeTimeTableScreen() {
    GONGBAEKTheme {
        ChangeTimeTableScreen()
    }
}
