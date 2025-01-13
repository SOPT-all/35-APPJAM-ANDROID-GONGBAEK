package com.sopt.gongbaek.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.model.MainBottomNavBarTabRoute
import com.sopt.gongbaek.presentation.model.Route

enum class MainBottomNavBarTabType(
    @DrawableRes val iconRes: Int,
    @StringRes val label: Int,
    val route: MainBottomNavBarTabRoute
) {
    GROUP_LIST(
        iconRes = R.drawable.ic_launcher_background,
        label = R.string.main_bottom_navbar_group_list,
        route = MainBottomNavBarTabRoute.GroupList
    ),
    MY_GROUP(
        iconRes = R.drawable.ic_launcher_background,
        label = R.string.main_bottom_navbar_my_group,
        route = MainBottomNavBarTabRoute.MyGroup
    ),
    HOME(
        iconRes = R.drawable.ic_launcher_background,
        label = R.string.main_bottom_navbar_home,
        route = MainBottomNavBarTabRoute.Home
    ),
    TIMETABLE(
        iconRes = R.drawable.ic_launcher_background,
        label = R.string.main_bottom_navbar_timetable,
        route = MainBottomNavBarTabRoute.TimeTable
    ),
    MY_PAGE(
        iconRes = R.drawable.ic_launcher_background,
        label = R.string.main_bottom_navbar_my_page,
        route = MainBottomNavBarTabRoute.MyPage
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainBottomNavBarTabRoute) -> Boolean): MainBottomNavBarTabType? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
