package com.sopt.gongbaek.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.model.NavigationRoute

enum class MainBottomNavBarTabType(
    @DrawableRes val iconRes: Int,
    @StringRes val label: Int,
    val route: String
) {
    GROUP_LIST(
        iconRes = R.drawable.ic_android_black_24dp,
        label = R.string.main_bottom_navbar_group_list,
        route = NavigationRoute.MainBottomNavBarTabRoute.GROUP_LIST_TAB
    ),
    MY_GROUP(
        iconRes = R.drawable.ic_android_black_24dp,
        label = R.string.main_bottom_navbar_my_group,
        route = NavigationRoute.MainBottomNavBarTabRoute.MY_GROUP_TAB
    ),
    HOME(
        iconRes = R.drawable.ic_android_black_24dp,
        label = R.string.main_bottom_navbar_home,
        route = NavigationRoute.MainBottomNavBarTabRoute.HOME_TAB
    ),
    TIMETABLE(
        iconRes = R.drawable.ic_android_black_24dp,
        label = R.string.main_bottom_navbar_timetable,
        route = NavigationRoute.MainBottomNavBarTabRoute.TIMETABLE_TAB
    ),
    MY_PAGE(
        iconRes = R.drawable.ic_android_black_24dp,
        label = R.string.main_bottom_navbar_my_page,
        route = NavigationRoute.MainBottomNavBarTabRoute.MY_PAGE_TAB
    );

    companion object {
        fun find(route: String): MainBottomNavBarTabType? = entries.find { it.route == route + "_route" }
        fun contains(route: String): Boolean = entries.any { it.route == route }
    }
}
