package com.sopt.gongbaek.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.model.NavigationRoute

enum class MainBottomNavBarTabType(
    @DrawableRes val selectedIconRes: Int,
    @DrawableRes val unselectedIconRes: Int,
    @StringRes val label: Int,
    val route: String
) {
    GROUP_LIST(
        selectedIconRes = R.drawable.ic_navi_fill_black_26,
        unselectedIconRes = R.drawable.ic_navi_fill_gray_26,
        label = R.string.main_bottom_navbar_group_list,
        route = NavigationRoute.MainBottomNavBarTabRoute.GROUP_LIST_TAB
    ),
    MY_GROUP(
        selectedIconRes = R.drawable.ic_navi_my_fill_black_26,
        unselectedIconRes = R.drawable.ic_navi_my_fill_gray_26,
        label = R.string.main_bottom_navbar_my_group,
        route = NavigationRoute.MainBottomNavBarTabRoute.MY_GROUP_TAB
    ),
    HOME(
        selectedIconRes = R.drawable.ic_navi_home_black_26,
        unselectedIconRes = R.drawable.ic_navi_home_gray_26,
        label = R.string.main_bottom_navbar_home,
        route = NavigationRoute.MainBottomNavBarTabRoute.HOME_TAB
    ),
    TIMETABLE(
        selectedIconRes = R.drawable.ic_navi_timetable_black_26,
        unselectedIconRes = R.drawable.ic_navi_timetable_gray_26,
        label = R.string.main_bottom_navbar_timetable,
        route = NavigationRoute.MainBottomNavBarTabRoute.TIMETABLE_TAB
    ),
    MY_PAGE(
        selectedIconRes = R.drawable.ic_navi_my_black_26,
        unselectedIconRes = R.drawable.ic_navi_my_gray_26,
        label = R.string.main_bottom_navbar_my_page,
        route = NavigationRoute.MainBottomNavBarTabRoute.MY_PAGE_TAB
    );

    companion object {
        fun find(route: String): MainBottomNavBarTabType? = entries.find { it.route == route + "_route" }
        fun contains(route: String): Boolean = entries.any { it.route == route }
    }
}
