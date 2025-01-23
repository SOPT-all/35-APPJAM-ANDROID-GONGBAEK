package com.sopt.gongbaek.presentation.ui.home.screen

import com.sopt.gongbaek.domain.model.NearestGroup
import com.sopt.gongbaek.domain.model.RecommendGroupInfo
import com.sopt.gongbaek.domain.model.UserLectureTimeTable
import com.sopt.gongbaek.domain.model.UserProfile
import com.sopt.gongbaek.presentation.ui.grouplist.screen.GroupListContract.SideEffect
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState
import com.sopt.gongbaek.presentation.util.nearestGroupFormatSchedule

class HomeContract {

    data class State(
        val homeLoadState: UiLoadState = UiLoadState.Idle,
        val userProfile: UserProfile = UserProfile(),
        val nearestGroup: NearestGroup = NearestGroup(),
        val nearestGroupSchedule: String = nearestGroupFormatSchedule(
            nearestGroup.weekDate,
            nearestGroup.startTime,
            nearestGroup.endTime
        ),
        val onceRecommendGroupList: List<RecommendGroupInfo> = emptyList(),
        val weekRecommendGroupList: List<RecommendGroupInfo> = emptyList(),
        val userLectureTimeTable: List<UserLectureTimeTable> = emptyList(),
        val convertedUserLectureTimeTable: Map<String, List<Int>> = emptyMap()
    ) : UiState

    sealed class Event : UiEvent {
        data object OnFetchHomeInfo : Event()
        data object OnFetchLatestOnceGroup : Event()
        data object OnFetchLatestWeekGroup : Event()
        data object OnFetchUserProfile : Event()
        data object OnFetchUserLectureTimetable : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data class NavigateToGroupDetail(val groupId: Int, val groupType: String) : SideEffect
        data object NavigateToGroupList : SideEffect
    }
}
