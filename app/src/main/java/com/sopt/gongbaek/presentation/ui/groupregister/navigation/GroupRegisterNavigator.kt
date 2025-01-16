package com.sopt.gongbaek.presentation.ui.groupregister.navigation

import androidx.navigation.NavController
import com.sopt.gongbaek.presentation.model.NavigationRoute

fun NavController.navigateGroupRegisterNavGraph() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER_NAV_GRAPH)
}

fun NavController.navigateGroupRegister() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER)
}
