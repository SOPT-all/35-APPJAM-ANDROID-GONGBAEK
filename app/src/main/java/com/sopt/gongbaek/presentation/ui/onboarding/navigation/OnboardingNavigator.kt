package com.sopt.gongbaek.presentation.ui.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateSelectProfile(navOptions: NavOptions) {
    navigate(
        route = "select_profile",
        navOptions = navOptions
    )
}

fun NavController.navigateNickname(navOptions: NavOptions) {
    navigate(
        route = "nickname",
        navOptions = navOptions
    )
}

fun NavController.navigateUnivMajor(navOptions: NavOptions) {
    navigate(
        route = "univ_major",
        navOptions = navOptions
    )
}

fun NavController.navigateUnivSearch(navOptions: NavOptions) {
    navigate(
        route = "univ_search",
        navOptions = navOptions
    )
}

fun NavController.navigateMajorSearch(navOptions: NavOptions) {
    navigate(
        route = "major_search",
        navOptions = navOptions
    )
}

fun NavController.navigateGrade(navOptions: NavOptions) {
    navigate(
        route = "grade",
        navOptions = navOptions
    )
}

fun NavController.navigateMbti(navOptions: NavOptions) {
    navigate(
        route = "mbti",
        navOptions = navOptions
    )
}

fun NavController.navigateGender(navOptions: NavOptions) {
    navigate(
        route = "gender",
        navOptions = navOptions
    )
}

fun NavController.navigateSelfIntroduction(navOptions: NavOptions) {
    navigate(
        route = "self_introduction",
        navOptions = navOptions
    )
}

fun NavController.navigateEnterTimeTable(navOptions: NavOptions) {
    navigate(
        route = "enter_timetable",
        navOptions = navOptions
    )
}

fun NavController.navigateGapTimeTable(navOptions: NavOptions) {
    navigate(
        route = "gap_timetable",
        navOptions = navOptions
    )
}

fun NavController.navigateCompleteOnboarding(navOptions: NavOptions) {
    navigate(
        route = "complete_onboarding",
        navOptions = navOptions
    )
}
