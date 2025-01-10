package com.sopt.gongbaek.presentation.ui.mygroup.navigation

import androidx.navigation.NavController

fun NavController.navigateMyGroupRoute() {
    navigate(route = "my_group_route")
}

fun NavController.navigateMyGroup() {
    navigate(route = "my_group")
}

fun NavController.navigateGroupRoom() {
    navigate(route = "group_room")
}
