package com.sopt.gongbaek.presentation.ui.home.navigation

import androidx.navigation.NavController

fun NavController.navigateHomeRoute() {
    navigate(route = "home_route")
}

fun NavController.navigateHome() {
    navigate(route = "home")
}
