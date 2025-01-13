package com.sopt.gongbaek.presentation.ui.groupdetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.groupdetail.screen.GroupDetailRoute
import com.sopt.gongbaek.presentation.ui.mygroup.navigation.navigateGroupRoom

fun NavGraphBuilder.groupDetailNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationRoute.GroupDetailNavGraphRoute.GROUP_DETAIL,
        route = NavigationRoute.GroupDetailNavGraphRoute.GROUP_DETAIL_NAV_GRAPH
    ) {
        composable(
            route = NavigationRoute.GroupDetailNavGraphRoute.GROUP_DETAIL
        ) {
            GroupDetailRoute(
                navigateGroupRoom = { navController.navigateGroupRoom() }
            )
        }
    }
}
