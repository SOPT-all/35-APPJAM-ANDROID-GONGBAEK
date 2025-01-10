package com.sopt.gongbaek.presentation.ui.mygroup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateMyGroup() {
    navigate(route = "my_group")
}

fun NavController.navigateGroupRoom() {
    navigate(route = "group_room")
}
