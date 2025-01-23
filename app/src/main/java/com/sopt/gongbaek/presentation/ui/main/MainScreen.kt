package com.sopt.gongbaek.presentation.ui.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.gongbaek.presentation.type.MainBottomNavBarTabType
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun MainScreen(
    navigator: MainNavigator,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars),
        bottomBar = {
            MainBottomNavBar(
                isVisible = navigator.showBottomBar(),
                bottomNavBarTabTypes = MainBottomNavBarTabType.entries.toList(),
                currentBottomNavBarTab = navigator.currentMainBottomNavBarTab,
                onBottomNavBarTabSelected = { navigator.navigate(it) }
            )
        },
        containerColor = GongBaekTheme.colors.white,
        content = { paddingValues ->
            MainNavHost(
                navigator = navigator,
                paddingValues = paddingValues
            )
        }
    )
}
