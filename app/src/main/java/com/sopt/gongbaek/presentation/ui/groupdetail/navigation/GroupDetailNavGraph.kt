package com.sopt.gongbaek.presentation.ui.groupdetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.ui.groupdetail.screen.GroupDetailRoute

fun NavGraphBuilder.groupDetailNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = "group_detail",
        route = "group_detail_route"
    ) {
        composable(
            route = "group_detail"
        ) {
            GroupDetailRoute()
        }
    }
}
