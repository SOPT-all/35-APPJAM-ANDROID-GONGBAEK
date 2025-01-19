package com.sopt.gongbaek.presentation.model

object NavigationRoute {
    object AuthNavGraphRoute {
        const val AUTH_NAV_GRAPH = "auth_route"
        const val SELECT_PROFILE = "select_profile"
        const val NICKNAME = "nickname"
        const val UNIV_MAJOR = "univ_major"
        const val GRADE = "grade"
        const val MBTI = "mbti"
        const val GENDER = "gender"
        const val SELF_INTRODUCTION = "self_introduction"
        const val ENTER_TIMETABLE = "enter_timetable"
        const val GAP_TIMETABLE = "gap_timetable"
        const val COMPLETE_AUTH = "complete_auth"
    }

    object MainBottomNavBarTabRoute {
        const val GROUP_LIST_TAB = "group_list_route"
        const val MY_GROUP_TAB = "my_group_route"
        const val HOME_TAB = "home_route"
        const val TIMETABLE_TAB = "timetable_route"
        const val MY_PAGE_TAB = "my_page_route"
    }

    object GroupDetailNavGraphRoute {
        const val GROUP_DETAIL_NAV_GRAPH = "group_detail_route"
        const val GROUP_DETAIL = "group_detail"
    }

    object GroupListNavGraphRoute {
        const val GROUP_LIST = "group_list"
    }

    object GroupRegisterNavGraphRoute {
        const val GROUP_REGISTER_NAV_GRAPH = "group_register_route"
        const val GROUP_REGISTER = "group_register"
    }

    object HomeNavGraphNavGraphRoute {
        const val HOME = "home"
    }

    object MyGroupNavGraphRoute {
        const val MY_GROUP = "my_group"
    }

    object GroupRoomNavGraphRoute {
        const val GROUP_ROOM_NAV_GRAPH = "group_room_route"
        const val GROUP_ROOM = "group_room"
    }

    object SplashRoute {
        const val SPLASH = "splash"
    }
}
