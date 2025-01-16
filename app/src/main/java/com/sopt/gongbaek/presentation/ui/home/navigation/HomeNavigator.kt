package com.sopt.gongbaek.presentation.ui.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.sopt.gongbaek.presentation.model.NavigationRoute

fun NavController.navigateHomeNavGraph(navOptions: NavOptions) {
    navigate(
        route = NavigationRoute.MainBottomNavBarTabRoute.HOME_TAB,
        navOptions = navOptions
    )
}

fun NavController.navigateHome() {
    navigate(route = NavigationRoute.HomeNavGraphNavGraphRoute.HOME)
}
