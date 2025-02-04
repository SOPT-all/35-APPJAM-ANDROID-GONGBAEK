package com.sopt.gongbaek.presentation.model

object NavigationRoute {
    object OnboardingNavGraph {
        const val ONBOARDING_NAV_GRAPH = "onboarding_route"
        const val ONBOARDING_MAIN = "onboarding"
    }

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
        const val TIMETABLE_CONVERT = "timetable_convert"
        const val COMPLETE_AUTH = "complete_auth"
        const val UNIV_SEARCH = "univ_search"
        const val MAJOR_SEARCH = "major_search"
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
        const val GROUP_DETAIL = "group_detail/{groupId}/{groupCycle}"
    }

    object GroupListNavGraphRoute {
        const val GROUP_LIST = "group_list"
    }

    object GroupRegisterNavGraphRoute {
        const val GROUP_REGISTER_NAV_GRAPH = "group_register_route"
        const val GROUP_CYCLE = "group_cycle"
        const val SELECT_DAY = "select_day"
        const val SELECT_DAY_OF_WEEK = "select_day_of_week"
        const val GROUP_TIME = "group_time"
        const val GROUP_CATEGORY = "group_category"
        const val GROUP_COVER = "group_cover"
        const val GROUP_PLACE_PEOPLE = "group_place_people"
        const val GROUP_INTRODUCTION = "group_introduction"
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
        const val GROUP_ROOM = "group_room/{groupId}/{groupCycle}"
    }

    object SplashRoute {
        const val SPLASH = "splash"
    }
}
