package com.sopt.gongbaek.presentation.ui.auth.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.NicknameRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.AuthViewModel
import com.sopt.gongbaek.presentation.ui.auth.screen.SelectProfileRoute

fun NavGraphBuilder.AuthNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = NavigationRoute.AuthNavGraphRoute.SELECT_PROFILE,
        route = NavigationRoute.AuthNavGraphRoute.AUTH_NAV_GRAPH
    ) {
        composable(
            route = NavigationRoute.AuthNavGraphRoute.SELECT_PROFILE
        ) {
            val viewModel: AuthViewModel = hiltViewModel(
                navController.getBackStackEntry(NavigationRoute.AuthNavGraphRoute.AUTH_NAV_GRAPH)
            )
            SelectProfileRoute(
                viewModel = viewModel,
                navigateNickname = navController::navigateNickname
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.NICKNAME
        ) {
            val viewModel: AuthViewModel = hiltViewModel(
                navController.getBackStackEntry(NavigationRoute.AuthNavGraphRoute.AUTH_NAV_GRAPH)
            )
            NicknameRoute(
                viewModel = viewModel,
                navigateUnivMajor = navController::navigateUnivMajor,
                naviToBack = navController::popBackStack
            )
        }
    }
}
