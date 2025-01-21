package com.sopt.gongbaek.presentation.ui.groupdetail.navigation

import androidx.navigation.NavController
import com.sopt.gongbaek.presentation.model.NavigationRoute

fun NavController.navigateGroupDetailNavGraph() {
    navigate(route = NavigationRoute.GroupDetailNavGraphRoute.GROUP_DETAIL_NAV_GRAPH)
}

fun NavController.navigateGroupDetail(groupId: Int, groupCycle: String) {
    navigate(route = NavigationRoute.GroupDetailNavGraphRoute.GROUP_DETAIL.replace("{groupId}", groupId.toString()).replace("{groupCycle}", groupCycle))
}
