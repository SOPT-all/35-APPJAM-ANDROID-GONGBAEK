package com.sopt.gongbaek.presentation.ui.grouplist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.sopt.gongbaek.presentation.model.NavigationRoute

fun NavController.navigateGroupListNavGraph(navOptions: NavOptions) {
    navigate(
        route = NavigationRoute.MainBottomNavBarTabRoute.GROUP_LIST_TAB,
        navOptions = navOptions
    )
}

fun NavController.navigateGroupList() {
    navigate(route = NavigationRoute.GroupListNavGraphRoute.GROUP_LIST)
}
