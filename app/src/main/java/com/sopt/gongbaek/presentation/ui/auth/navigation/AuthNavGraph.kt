package com.sopt.gongbaek.presentation.ui.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.AuthViewModel
import com.sopt.gongbaek.presentation.ui.auth.screen.NicknameRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.SelectProfileRoute

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    navigation(
        startDestination = NavigationRoute.AuthNavGraphRoute.SELECT_PROFILE,
        route = NavigationRoute.AuthNavGraphRoute.AUTH_NAV_GRAPH
    ) {
        composable(
            route = NavigationRoute.AuthNavGraphRoute.SELECT_PROFILE
        ) {
            SelectProfileRoute(
                viewModel = viewModel,
                navigateNickname = navController::navigateNickname
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.NICKNAME
        ) {
            NicknameRoute(
                viewModel = viewModel,
                navigateUnivMajor = navController::navigateUnivMajor,
                navigateBack = navController::popBackStack
            )
        }
    }
}
