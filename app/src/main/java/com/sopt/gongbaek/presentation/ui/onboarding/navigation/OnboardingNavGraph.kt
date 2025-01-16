package com.sopt.gongbaek.presentation.ui.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.home.navigation.navigateHome
import com.sopt.gongbaek.presentation.ui.onboarding.screen.OnboardingRoute

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationRoute.OnboardingNavGraphRoute.ONBOARDING,
        route = NavigationRoute.OnboardingNavGraphRoute.ONBOARDING_NAV_GRAPH
    ) {
        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.ONBOARDING
        ) {
            OnboardingRoute(
                navigateHome = { navController.navigateHome() }
            )
        }
    }
}
