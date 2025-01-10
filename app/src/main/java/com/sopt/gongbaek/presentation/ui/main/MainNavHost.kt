package com.sopt.gongbaek.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.gongbaek.presentation.ui.groupdetail.navigation.GroupDetailNavGraph
import com.sopt.gongbaek.presentation.ui.grouplist.navigation.GroupListNavGraph
import com.sopt.gongbaek.presentation.ui.groupregister.navigation.GroupRegisterNavGraph
import com.sopt.gongbaek.presentation.ui.home.navigation.HomeNavGraph
import com.sopt.gongbaek.presentation.ui.mygroup.navigation.MyGroupNavGraph
import com.sopt.gongbaek.presentation.ui.onboarding.navigation.OnboardingNavGraph
import com.sopt.gongbaek.presentation.ui.splash.SplashScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NavHost(
            navController = navController,
            startDestination = "splash"
        ) {
            composable(route = "splash") { SplashScreen(navController = navController) }
            OnboardingNavGraph(navController)
            GroupListNavGraph(navController)
            GroupRegisterNavGraph(navController)
            GroupDetailNavGraph(navController)
            MyGroupNavGraph(navController)
            HomeNavGraph(navController)
        }
    }
}
