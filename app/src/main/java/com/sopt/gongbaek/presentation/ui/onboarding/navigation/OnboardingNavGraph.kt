package com.sopt.gongbaek.presentation.ui.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.auth.navigation.navigateAuthRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.OnboardingScreen

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationRoute.OnboardingNavGraph.ONBOARDING_MAIN,
        route = NavigationRoute.OnboardingNavGraph.ONBOARDING_NAV_GRAPH
    ) {
        composable(
            route = NavigationRoute.OnboardingNavGraph.ONBOARDING_MAIN
        ) {
            OnboardingScreen(
                navigateAuth = { navController.navigateAuthRoute() }
            )
        }
    }
}
