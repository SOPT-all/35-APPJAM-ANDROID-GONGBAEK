package com.sopt.gongbaek.presentation.ui.grouproom.navigation

import androidx.navigation.NavController
import com.sopt.gongbaek.presentation.model.NavigationRoute

fun NavController.navigateGroupRoomNavGraph() {
    navigate(route = NavigationRoute.GroupRoomNavGraphRoute.GROUP_ROOM_NAV_GRAPH)
}

fun NavController.navigateGroupRoom() {
    navigate(route = NavigationRoute.GroupRoomNavGraphRoute.GROUP_ROOM)
}