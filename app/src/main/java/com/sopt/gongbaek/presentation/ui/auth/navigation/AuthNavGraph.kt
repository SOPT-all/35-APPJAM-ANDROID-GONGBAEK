package com.sopt.gongbaek.presentation.ui.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.AuthViewModel
import com.sopt.gongbaek.presentation.ui.auth.screen.ChangeTimeTableRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.CompleteAuthRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.EnterTimeTableRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.GapTimeTableRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.GenderRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.GradeRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.MajorSearchRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.MbtiRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.NicknameRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.SelectProfileRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.SelfIntroductionRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.UnivMajorRoute
import com.sopt.gongbaek.presentation.ui.auth.screen.UnivSearchRoute
import com.sopt.gongbaek.presentation.ui.home.navigation.navigateHome

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

        composable(
            route = NavigationRoute.AuthNavGraphRoute.UNIV_MAJOR
        ) {
            UnivMajorRoute(
                viewModel = viewModel,
                navigateGrade = navController::navigateGrade,
                navigateUnivSearch = navController::navigateUnivSearch,
                navigateMajorSearch = navController::navigateMajorSearch,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.UNIV_SEARCH
        ) {
            UnivSearchRoute(
                viewModel = viewModel,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.MAJOR_SEARCH
        ) {
            MajorSearchRoute(
                viewModel = viewModel,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.GRADE
        ) {
            GradeRoute(
                viewModel = viewModel,
                navigateMbti = navController::navigateMbti,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.MBTI
        ) {
            MbtiRoute(
                viewModel = viewModel,
                navigateGender = navController::navigateGender,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.GENDER
        ) {
            GenderRoute(
                viewModel = viewModel,
                navigateSelfIntroduction = navController::navigateSelfIntroduction,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.SELF_INTRODUCTION
        ) {
            SelfIntroductionRoute(
                viewModel = viewModel,
                navigateEnterTimetable = navController::navigateEnterTimetable,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.ENTER_TIMETABLE
        ) {
            EnterTimeTableRoute(
                viewModel = viewModel,
                navigateGapTimetable = navController::navigateGapTimetable,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.GAP_TIMETABLE
        ) {
            GapTimeTableRoute(
                viewModel = viewModel,
                navigateCompleteAuth = navController::navigateCompleteAuth,
                navigateEnterTimetable = navController::navigateEnterTimetable
            )
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.CHANGE_TIMETABLE
        ) {
            ChangeTimeTableRoute()
        }

        composable(
            route = NavigationRoute.AuthNavGraphRoute.COMPLETE_AUTH
        ) {
            CompleteAuthRoute(
                viewModel = viewModel,
                navigateHome = navController::navigateHome
            )
        }
    }
}
