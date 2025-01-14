package com.sopt.gongbaek.presentation.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.home.screen.HomeRoute

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationRoute.HomeNavGraphNavGraphRoute.HOME,
        route = NavigationRoute.MainBottomNavBarTabRoute.HOME_TAB
    ) {
        composable(
            route = NavigationRoute.HomeNavGraphNavGraphRoute.HOME
        ) {
            HomeRoute()
        }
    }
}
