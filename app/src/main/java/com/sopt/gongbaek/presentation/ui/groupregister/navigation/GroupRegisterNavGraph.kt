package com.sopt.gongbaek.presentation.ui.groupregister.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.grouplist.navigation.navigateGroupList
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupCategoryRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupCoverRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupCycleRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupIntroductionRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupPlacePeopleRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupRegisterRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupRegisterViewModel
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupTimeRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.SelectDayOfWeekRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.SelectDayRoute

fun NavGraphBuilder.groupRegisterNavGraph(
    navController: NavHostController,
    viewModel: GroupRegisterViewModel
) {
    navigation(
        startDestination = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_CYCLE,
        route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER_NAV_GRAPH
    ) {
        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_CYCLE
        ) {
            GroupCycleRoute(
                viewModel = viewModel,
                navigateDay = navController::navigateSelectDay,
                navigateDayOfWeek = navController::navigateSelectDayOfWeek,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.SELECT_DAY
        ) {
            SelectDayRoute(
                viewModel = viewModel,
                navigateGroupTime = navController::navigateGroupTime,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.SELECT_DAY_OF_WEEK
        ) {
            SelectDayOfWeekRoute(
                viewModel = viewModel,
                navigateGroupTime = navController::navigateGroupTime,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_TIME
        ) {
            GroupTimeRoute(
                viewModel = viewModel,
                navigateGroupCategory = navController::navigateGroupCategory,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_CATEGORY
        ) {
            GroupCategoryRoute(
                viewModel = viewModel,
                navigateGroupCover = navController::navigateGroupCover,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_COVER
        ) {
            GroupCoverRoute(
                viewModel = viewModel,
                navigateGroupPlacePeople = navController::navigateGroupPlacePeople,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_PLACE_PEOPLE
        ) {
            GroupPlacePeopleRoute(
                viewModel = viewModel,
                navigateGroupIntroduction = navController::navigateGroupIntroduction,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_INTRODUCTION
        ) {
            GroupIntroductionRoute(
                viewModel = viewModel,
                navigateRegister = navController::navigateGroupRegister,
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER
        ) {
            GroupRegisterRoute(
                viewModel = viewModel,
                navigateGroupList = navController::navigateGroupList,
                navigateBack = navController::popBackStack
            )
        }
    }
}
