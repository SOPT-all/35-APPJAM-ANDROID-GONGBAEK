package com.sopt.gongbaek.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.gongbaek.presentation.ui.groupdetail.navigation.groupDetailNavGraph
import com.sopt.gongbaek.presentation.ui.grouplist.navigation.groupListNavGraph
import com.sopt.gongbaek.presentation.ui.groupregister.navigation.groupRegisterNavGraph
import com.sopt.gongbaek.presentation.ui.home.navigation.homeNavGraph
import com.sopt.gongbaek.presentation.ui.mygroup.navigation.myGroupNavGraph
import com.sopt.gongbaek.presentation.ui.onboarding.navigation.onboardingNavGraph
import com.sopt.gongbaek.presentation.ui.splash.SplashScreen
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GongBaekTheme.colors.white)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination::class.simpleName.orEmpty()
        ) {
            composable(route = "splash") { SplashScreen(navController = navigator.navController) }
            onboardingNavGraph(navigator.navController)
            groupListNavGraph(navigator.navController)
            groupRegisterNavGraph(navigator.navController)
            groupDetailNavGraph(navigator.navController)
            myGroupNavGraph(navigator.navController)
            homeNavGraph(navigator.navController)
        }
    }
}
