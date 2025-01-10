package com.sopt.gongbaek.presentation.ui.mygroup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateMyGroup(navOptions: NavOptions) {
    navigate(
        route = "my_group",
        navOptions = navOptions
    )
}

fun NavController.navigateGroupRoom(navOptions: NavOptions) {
    navigate(
        route = "group_room",
        navOptions = navOptions
    )
}
