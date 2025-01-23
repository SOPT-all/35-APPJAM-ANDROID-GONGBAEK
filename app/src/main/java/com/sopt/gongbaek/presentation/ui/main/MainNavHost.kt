package com.sopt.gongbaek.presentation.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.auth.navigation.authNavGraph
import com.sopt.gongbaek.presentation.ui.auth.screen.AuthViewModel
import com.sopt.gongbaek.presentation.ui.groupdetail.navigation.groupDetailNavGraph
import com.sopt.gongbaek.presentation.ui.grouplist.navigation.groupListNavGraph
import com.sopt.gongbaek.presentation.ui.groupregister.navigation.groupRegisterNavGraph
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupRegisterViewModel
import com.sopt.gongbaek.presentation.ui.grouproom.navigation.groupRoomNavGraph
import com.sopt.gongbaek.presentation.ui.home.navigation.homeNavGraph
import com.sopt.gongbaek.presentation.ui.mygroup.navigation.myGroupNavGraph
import com.sopt.gongbaek.presentation.ui.onboarding.navigation.onboardingNavGraph
import com.sopt.gongbaek.presentation.ui.splash.SplashScreen

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val currentBackStackEntry by navigator.navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val authViewModel: AuthViewModel = hiltViewModel()
    val groupRegisterViewModel: GroupRegisterViewModel = hiltViewModel()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                if (currentRoute == NavigationRoute.HomeNavGraphNavGraphRoute.HOME ||
                    currentRoute == NavigationRoute.GroupRoomNavGraphRoute.GROUP_ROOM
                ) {
                    PaddingValues(0.dp)
                } else {
                    PaddingValues(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
                }
            )
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination
        ) {
            composable(route = NavigationRoute.SplashRoute.SPLASH) { SplashScreen(navController = navigator.navController) }
            onboardingNavGraph(navigator.navController)
            authNavGraph(
                navController = navigator.navController,
                viewModel = authViewModel
            )
            groupListNavGraph(navigator.navController)
            groupRegisterNavGraph(
                navController = navigator.navController,
                viewModel = groupRegisterViewModel
            )
            groupDetailNavGraph(navigator.navController)
            myGroupNavGraph(navigator.navController)
            groupRoomNavGraph(navigator.navController)
            homeNavGraph(navigator.navController)
        }
    }
}
