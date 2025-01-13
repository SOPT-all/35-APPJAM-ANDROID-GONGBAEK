package com.sopt.gongbaek.presentation.ui.mygroup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.sopt.gongbaek.presentation.model.NavigationRoute

fun NavController.navigateMyGroupNavGraph(navOptions: NavOptions) {
    navigate(
        route = NavigationRoute.MainBottomNavBarTabRoute.MY_GROUP_TAB,
        navOptions = navOptions
    )
}

fun NavController.navigateMyGroup() {
    navigate(route = NavigationRoute.MyGroupNavGraphRoute.MY_GROUP)
}
