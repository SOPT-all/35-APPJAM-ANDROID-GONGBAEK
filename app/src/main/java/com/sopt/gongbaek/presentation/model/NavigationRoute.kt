package com.sopt.gongbaek.presentation.model

sealed interface Route

sealed interface MainBottomNavBarTabRoute : Route {
    data object GroupList : MainBottomNavBarTabRoute
    data object MyGroup : MainBottomNavBarTabRoute
    data object Home : MainBottomNavBarTabRoute
    data object TimeTable : MainBottomNavBarTabRoute
    data object MyPage : MainBottomNavBarTabRoute
}
