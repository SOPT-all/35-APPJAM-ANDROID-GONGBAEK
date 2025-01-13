package com.sopt.gongbaek.presentation.ui.grouplist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.grouplist.screen.GroupListRoute

fun NavGraphBuilder.groupListNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationRoute.GroupListNavGraphRoute.GROUP_LIST,
        route = NavigationRoute.MainBottomNavBarTabRoute.GROUP_LIST_TAB
    ) {
        composable(
            route = NavigationRoute.GroupListNavGraphRoute.GROUP_LIST
        ) {
            GroupListRoute()
        }
    }
}
