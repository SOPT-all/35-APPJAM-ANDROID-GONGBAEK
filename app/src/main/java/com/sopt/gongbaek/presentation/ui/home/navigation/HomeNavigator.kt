package com.sopt.gongbaek.presentation.ui.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.sopt.gongbaek.presentation.model.MainBottomNavBarTabRoute

fun NavController.navigateHomeRoute() {
    navigate(route = "home_route")
}

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(
        route = MainBottomNavBarTabRoute.Home::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}
