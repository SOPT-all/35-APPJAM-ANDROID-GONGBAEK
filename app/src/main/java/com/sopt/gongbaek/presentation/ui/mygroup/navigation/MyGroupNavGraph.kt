package com.sopt.gongbaek.presentation.ui.mygroup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.ui.groupdetail.navigation.navigateGroupDetail
import com.sopt.gongbaek.presentation.ui.grouproom.navigation.navigateGroupRoom
import com.sopt.gongbaek.presentation.ui.mygroup.screen.MyGroupRoute

fun NavGraphBuilder.myGroupNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavigationRoute.MyGroupNavGraphRoute.MY_GROUP,
        route = NavigationRoute.MainBottomNavBarTabRoute.MY_GROUP_TAB
    ) {
        composable(
            route = NavigationRoute.MyGroupNavGraphRoute.MY_GROUP
        ) {
            MyGroupRoute(
                navigateGroupDetail = { groupId, groupCycle -> navController.navigateGroupDetail(groupId, groupCycle) },
                navigateGroupRoom = { groupId, groupCycle -> navController.navigateGroupRoom(groupId, groupCycle) }
            )
        }
    }
}
