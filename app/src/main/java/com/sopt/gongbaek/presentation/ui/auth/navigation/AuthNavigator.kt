package com.sopt.gongbaek.presentation.ui.auth.navigation

import androidx.navigation.NavController
import com.sopt.gongbaek.presentation.model.NavigationRoute

fun NavController.navigateAuthRoute() = navigate(route = NavigationRoute.AuthNavGraphRoute.AUTH_NAV_GRAPH)

fun NavController.navigateCompleteAuth() = navigate(route = NavigationRoute.AuthNavGraphRoute.AUTH)

fun NavController.navigateNickname() = navigate(route = NavigationRoute.AuthNavGraphRoute.NICKNAME)

fun NavController.navigateUnivMajor() = navigate(route = NavigationRoute.AuthNavGraphRoute.UNIV_MAJOR)
