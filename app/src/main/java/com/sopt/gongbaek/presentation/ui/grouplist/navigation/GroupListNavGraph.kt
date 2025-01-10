package com.sopt.gongbaek.presentation.ui.grouplist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.ui.grouplist.screen.GroupListRoute

fun NavGraphBuilder.GroupListNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = "group_list",
        route = "group_list_route"
    ) {
        composable(
            route = "group_list"
        ) {
            GroupListRoute()
        }
    }
}
