package com.sopt.gongbaek.presentation.ui.groupregister.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateGroupCycle(navOptions: NavOptions) {
    navigate(
        route = "group_cycle",
        navOptions = navOptions
    )
}

fun NavController.navigateSelectDay(navOptions: NavOptions) {
    navigate(
        route = "select_day",
        navOptions = navOptions
    )
}

fun NavController.navigateSelectDayOfWeek(navOptions: NavOptions) {
    navigate(
        route = "select_day_of_week",
        navOptions = navOptions
    )
}

fun NavController.navigateGroupTime(navOptions: NavOptions) {
    navigate(
        route = "group_time",
        navOptions = navOptions
    )
}

fun NavController.navigateGroupCategory(navOptions: NavOptions) {
    navigate(
        route = "group_category",
        navOptions = navOptions
    )
}

fun NavController.navigateGroupCover(navOptions: NavOptions) {
    navigate(
        route = "group_cover",
        navOptions = navOptions
    )
}

fun NavController.navigateGroupPlacePeople(navOptions: NavOptions) {
    navigate(
        route = "group_place_people",
        navOptions = navOptions
    )
}

fun NavController.navigateGroupIntroduction(navOptions: NavOptions) {
    navigate(
        route = "group_introduction",
        navOptions = navOptions
    )
}

fun NavController.navigateGroupRegister(navOptions: NavOptions) {
    navigate(
        route = "group_register",
        navOptions = navOptions
    )
}
