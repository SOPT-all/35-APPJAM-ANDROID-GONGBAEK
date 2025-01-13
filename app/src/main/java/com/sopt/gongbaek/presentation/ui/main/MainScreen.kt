package com.sopt.gongbaek.presentation.ui.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.gongbaek.presentation.type.MainBottomNavBarTabType

@Composable
fun MainScreen(
    navigator: MainNavigator,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            MainBottomNavBar(
                isVisible = navigator.showBottomBar(),
                bottomNavBarTabTypes = MainBottomNavBarTabType.entries.toList(),
                currentBottomNavBarTab = navigator.currentMainBottomNavBarTab,
                onBottomNavBarTabSelected = { navigator.navigate(it) }
            )
        },
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = padding
            )
        }
    )
}
