package com.sopt.gongbaek.presentation.ui.groupregister.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupCategoryRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupCoverRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupCycleRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupIntroductionRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupPlacePeopleRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupRegisterRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupTimeRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.SelectDayOfWeekRoute
import com.sopt.gongbaek.presentation.ui.groupregister.screen.SelectDayRoute

fun NavGraphBuilder.GroupRegisterNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = "group_cycle",
        route = "group_register_route"
    ) {
        composable(
            route = "group_cycle"
        ) {
            GroupCycleRoute()
        }

        composable(
            route = "select_day"
        ) {
            SelectDayRoute()
        }

        composable(
            route = "select_day_of_week"
        ) {
            SelectDayOfWeekRoute()
        }

        composable(
            route = "group_time"
        ) {
            GroupTimeRoute()
        }

        composable(
            route = "group_category"
        ) {
            GroupCategoryRoute()
        }

        composable(
            route = "group_cover"
        ) {
            GroupCoverRoute()
        }

        composable(
            route = "group_place_people"
        ) {
            GroupPlacePeopleRoute()
        }

        composable(
            route = "group_introduction"
        ) {
            GroupIntroductionRoute()
        }

        composable(
            route = "group_register"
        ) {
            GroupRegisterRoute()
        }
    }
}
