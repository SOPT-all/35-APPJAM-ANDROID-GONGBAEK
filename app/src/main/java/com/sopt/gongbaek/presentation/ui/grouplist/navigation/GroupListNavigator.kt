package com.sopt.gongbaek.presentation.ui.grouplist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateGroupList(navOptions: NavOptions) {
    navigate(route = "group_list")
}
