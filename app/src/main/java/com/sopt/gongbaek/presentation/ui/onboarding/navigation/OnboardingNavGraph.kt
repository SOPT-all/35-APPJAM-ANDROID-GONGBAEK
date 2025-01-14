package com.sopt.gongbaek.presentation.ui.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.CompleteOnboardingRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.EnterTimeTableRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.GapTimeTableRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.GenderRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.GradeRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.MajorSearchRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.MbtiRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.NicknameRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.SelectProfileRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.SelfIntroductionRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.UnivMajorRoute
import com.sopt.gongbaek.presentation.ui.onboarding.screen.UnivSearchRoute

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationRoute.OnboardingNavGraphRoute.SELECT_PROFILE,
        route = NavigationRoute.OnboardingNavGraphRoute.ONBOARDING_NAV_GRAPH
    ) {
        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.SELECT_PROFILE
        ) {
            SelectProfileRoute(
                navigateNickname = { navController.navigateNickname() }
            )
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.NICKNAME
        ) {
            NicknameRoute()
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.UNIV_MAJOR
        ) {
            UnivMajorRoute()
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.UNIV_SEARCH
        ) {
            UnivSearchRoute()
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.MAJOR_SEARCH
        ) {
            MajorSearchRoute()
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.GRADE
        ) {
            GradeRoute()
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.MBTI
        ) {
            MbtiRoute()
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.GENDER
        ) {
            GenderRoute()
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.SELF_INTRODUCTION
        ) {
            SelfIntroductionRoute()
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.ENTER_TIMETABLE
        ) {
            EnterTimeTableRoute()
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.GAP_TIMETABLE
        ) {
            GapTimeTableRoute()
        }

        composable(
            route = NavigationRoute.OnboardingNavGraphRoute.COMPLETE_ONBOARDING
        ) {
            CompleteOnboardingRoute()
        }
    }
}
