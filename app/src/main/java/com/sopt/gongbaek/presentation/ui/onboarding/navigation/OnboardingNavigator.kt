package com.sopt.gongbaek.presentation.ui.onboarding.navigation

import androidx.navigation.NavController
import com.sopt.gongbaek.presentation.model.NavigationRoute

fun NavController.navigateOnboardingRoute() {
    navigate(route = NavigationRoute.OnboardingNavGraphRoute.ONBOARDING_NAV_GRAPH)
}

fun NavController.navigateCompleteOnboarding() {
    navigate(route = NavigationRoute.OnboardingNavGraphRoute.ONBOARDING)
}
