package com.sopt.gongbaek.presentation.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen() {

}

@Preview(showBackground = true)
@Composable
fun ShowHomeScreen() {
    GONGBAEKTheme {
        HomeScreen()
    }
}