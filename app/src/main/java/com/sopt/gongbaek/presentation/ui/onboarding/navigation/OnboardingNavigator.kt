package com.sopt.gongbaek.presentation.ui.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateSelectProfile() {
    navigate(route = "select_profile")
}

fun NavController.navigateNickname() {
    navigate(route = "nickname")
}

fun NavController.navigateUnivMajor() {
    navigate(route = "univ_major")
}

fun NavController.navigateUnivSearch() {
    navigate(route = "univ_search")
}

fun NavController.navigateMajorSearch() {
    navigate(route = "major_search")
}

fun NavController.navigateGrade() {
    navigate(route = "grade")
}

fun NavController.navigateMbti() {
    navigate(route = "mbti")
}

fun NavController.navigateGender() {
    navigate(route = "gender")
}

fun NavController.navigateSelfIntroduction() {
    navigate(route = "self_introduction")
}

fun NavController.navigateEnterTimeTable() {
    navigate(route = "enter_timetable")
}

fun NavController.navigateGapTimeTable() {
    navigate(route = "gap_timetable")
}

fun NavController.navigateCompleteOnboarding() {
    navigate(route = "complete_onboarding")
}
