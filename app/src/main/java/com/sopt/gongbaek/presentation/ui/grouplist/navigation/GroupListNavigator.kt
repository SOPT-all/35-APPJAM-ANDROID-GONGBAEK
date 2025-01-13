package com.sopt.gongbaek.presentation.ui.grouplist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.sopt.gongbaek.presentation.model.MainBottomNavBarTabRoute

fun NavController.navigateGroupListRoute() {
    navigate(route = "group_list_route")
}

fun NavController.navigateGroupList(navOptions: NavOptions) {
    navigate(
        route = MainBottomNavBarTabRoute.GroupList::class.simpleName.orEmpty(),
        navOptions = navOptions
    )
}
