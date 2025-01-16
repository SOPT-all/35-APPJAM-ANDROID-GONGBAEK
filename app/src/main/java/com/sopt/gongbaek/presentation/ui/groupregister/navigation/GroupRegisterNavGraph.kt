package com.sopt.gongbaek.presentation.ui.groupregister.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.grouplist.navigation.navigateGroupList
import com.sopt.gongbaek.presentation.ui.groupregister.screen.GroupRegisterRoute

fun NavGraphBuilder.groupRegisterNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER,
        route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER_NAV_GRAPH
    ) {
        composable(
            route = NavigationRoute.GroupRegisterNavGraphRoute.GROUP_REGISTER
        ) {
            GroupRegisterRoute(
                navigateGroupList = { navController.navigateGroupList() }
            )
        }
    }
}
