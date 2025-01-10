package com.sopt.gongbaek.presentation.ui.mygroup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.gongbaek.presentation.ui.mygroup.screen.GroupRoomRoute
import com.sopt.gongbaek.presentation.ui.mygroup.screen.MyGroupRoute

fun NavGraphBuilder.MyGroupNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = "my_group",
        route = "my_group_route"
    ) {
        composable(
            route = "my_group"
        ) {
            MyGroupRoute()
        }

        composable(
            route = "group_room"
        ) {
            GroupRoomRoute()
        }
    }
}
