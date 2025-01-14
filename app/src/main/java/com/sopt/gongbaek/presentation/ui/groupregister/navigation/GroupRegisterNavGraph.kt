package com.sopt.gongbaek.presentation.ui.groupregister.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupCategoryRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupCoverRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupCycleRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupIntroductionRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupPlacePeopleRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupRegisterRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupTimeRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.SelectDayOfWeekRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.SelectDayRoute

fun NavGraphBuilder.groupRegisterNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_CYCLE,
        route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER_NAV_GRAPH
    ) {
        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_CYCLE
        ) {
            GroupCycleRoute()
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.SELECT_DAY
        ) {
            SelectDayRoute()
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.SELECT_DAY_OF_WEEK
        ) {
            SelectDayOfWeekRoute()
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_TIME
        ) {
            GroupTimeRoute()
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_CATEGORY
        ) {
            GroupCategoryRoute()
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_COVER
        ) {
            GroupCoverRoute()
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_PLACE_PEOPLE
        ) {
            GroupPlacePeopleRoute()
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_INTRODUCTION
        ) {
            GroupIntroductionRoute()
        }

        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER
        ) {
            GroupRegisterRoute()
        }
    }
}
