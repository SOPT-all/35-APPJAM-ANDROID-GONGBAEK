package com.sopt.gongbaek.presentation.ui.grouplist.navigation

import androidx.navigation.NavController

fun NavController.navigateGroupListRoute() {
    navigate(route = "group_list_route")
}

fun NavController.navigateGroupList() {
    navigate(route = "group_list")
}
