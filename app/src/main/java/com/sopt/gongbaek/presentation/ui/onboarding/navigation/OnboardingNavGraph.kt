package com.sopt.gongbaek.presentation.ui.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
        startDestination = "select_profile",
        route = "onboarding_route"
    ) {
        composable(
            route = "select_profile"
        ) {
            SelectProfileRoute(
                navigateNickname = { navController.navigateNickname() }
            )
        }

        composable(
            route = "nickname"
        ) {
            NicknameRoute()
        }

        composable(
            route = "univ_major"
        ) {
            UnivMajorRoute()
        }

        composable(
            route = "univ_search"
        ) {
            UnivSearchRoute()
        }

        composable(
            route = "major_search"
        ) {
            MajorSearchRoute()
        }

        composable(
            route = "grade"
        ) {
            GradeRoute()
        }

        composable(
            route = "mbti"
        ) {
            MbtiRoute()
        }

        composable(
            route = "gender"
        ) {
            GenderRoute()
        }

        composable(
            route = "self_introduction"
        ) {
            SelfIntroductionRoute()
        }

        composable(
            route = "enter_timetable"
        ) {
            EnterTimeTableRoute()
        }

        composable(
            route = "gap_timetable"
        ) {
            GapTimeTableRoute()
        }

        composable(
            route = "complete_onboarding"
        ) {
            CompleteOnboardingRoute()
        }
    }
}
