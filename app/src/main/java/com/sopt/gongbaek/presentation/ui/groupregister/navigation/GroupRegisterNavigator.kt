package com.sopt.gongbaek.presentation.ui.groupregister.navigation

import androidx.navigation.NavController
import com.sopt.gongbaek.presentation.model.NavigationRoute

fun NavController.navigateGroupRegisterNavGraph() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER_NAV_GRAPH)
}

fun NavController.navigateGroupCycle() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_CYCLE)
}

fun NavController.navigateSelectDay() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.SELECT_DAY)
}

fun NavController.navigateSelectDayOfWeek() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.SELECT_DAY_OF_WEEK)
}

fun NavController.navigateGroupTime() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_TIME)
}

fun NavController.navigateGroupCategory() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_CATEGORY)
}

fun NavController.navigateGroupCover() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_COVER)
}

fun NavController.navigateGroupPlacePeople() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_PLACE_PEOPLE)
}

fun NavController.navigateGroupIntroduction() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_INTRODUCTION)
}

fun NavController.navigateGroupRegister() {
    navigate(route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER)
}
