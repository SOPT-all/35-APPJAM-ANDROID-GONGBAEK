package com.sopt.gongbaek.presentation.ui.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
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
        modifier = modifier
            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Vertical)),
        bottomBar = {
            MainBottomNavBar(
                isVisible = navigator.showBottomBar(),
                bottomNavBarTabTypes = MainBottomNavBarTabType.entries.toList(),
                currentBottomNavBarTab = navigator.currentMainBottomNavBarTab,
                onBottomNavBarTabSelected = { navigator.navigate(it) }
            )
        },
        content = { paddingValues ->
            MainNavHost(
                navigator = navigator,
                paddingValues = paddingValues
            )
        }
    )
}
