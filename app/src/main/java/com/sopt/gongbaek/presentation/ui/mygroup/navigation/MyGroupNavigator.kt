package com.sopt.gongbaek.presentation.ui.mygroup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.sopt.gongbaek.presentation.model.MainBottomNavBarTabRoute

fun NavController.navigateMyGroupRoute() {
    navigate(route = "my_group_route")
}

fun NavController.navigateMyGroup(navOptions: NavOptions) {
    navigate(
        route = MainBottomNavBarTabRoute.MyGroup::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}

fun NavController.navigateGroupRoom() {
    navigate(route = "group_room")
}
