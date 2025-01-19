package com.sopt.gongbaek.presentation.ui.auth.navigation

import androidx.navigation.NavController
import com.sopt.gongbaek.presentation.model.NavigationRoute

fun NavController.navigateAuthRoute() = navigate(route = NavigationRoute.AuthNavGraphRoute.AUTH_NAV_GRAPH)

fun NavController.navigateNickname() = navigate(route = NavigationRoute.AuthNavGraphRoute.NICKNAME)

fun NavController.navigateUnivMajor() = navigate(route = NavigationRoute.AuthNavGraphRoute.UNIV_MAJOR)

fun NavController.navigateGrade() = navigate(route = NavigationRoute.AuthNavGraphRoute.GRADE)

fun NavController.navigateMbti() = navigate(route = NavigationRoute.AuthNavGraphRoute.MBTI)

fun NavController.navigateGender() = navigate(route = NavigationRoute.AuthNavGraphRoute.GENDER)

fun NavController.navigateSelfIntroduction() = navigate(route = NavigationRoute.AuthNavGraphRoute.SELF_INTRODUCTION)

fun NavController.navigateEnterTimetable() = navigate(route = NavigationRoute.AuthNavGraphRoute.ENTER_TIMETABLE)

fun NavController.navigateGapTimetable() = navigate(route = NavigationRoute.AuthNavGraphRoute.GAP_TIMETABLE)

fun NavController.navigateCompleteAuth() = navigate(route = NavigationRoute.AuthNavGraphRoute.COMPLETE_AUTH)
