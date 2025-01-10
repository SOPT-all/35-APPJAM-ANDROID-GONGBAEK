package com.sopt.gongbaek.presentation.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.ui.grouplist.navigation.navigateGroupList
import com.sopt.gongbaek.presentation.ui.home.screen.HomeRoute

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = "home",
        route = "home_route"
    ) {
        composable(
            route = "home"
        ) {
            HomeRoute(
                navigateGroupList = { navController.navigateGroupList() }
            )
        }
    }
}
