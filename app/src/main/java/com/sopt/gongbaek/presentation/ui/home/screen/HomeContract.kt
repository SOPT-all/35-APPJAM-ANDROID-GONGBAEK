package com.sopt.gongbaek.presentation.ui.home.screen

import com.sopt.gongbaek.domain.model.NearestGroup
import com.sopt.gongbaek.domain.model.RecommendGroupInfo
import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState
import com.sopt.gongbaek.presentation.util.nearestGroupFormatSchedule

class HomeContract {

    data class State(
        val homeLoadState: UiLoadState = UiLoadState.Idle,
        val userInfo: UserInfo = UserInfo(),
        val nearestGroup: NearestGroup = NearestGroup(),
        val nearestGroupSchedule: String = nearestGroupFormatSchedule(
            nearestGroup.weekDate,
            nearestGroup.startTime,
            nearestGroup.endTime
        ),
        val onceRecommendGroupList: List<RecommendGroupInfo> = emptyList(),
        val weekRecommendGroupList: List<RecommendGroupInfo> = emptyList()
    ) : UiState

    sealed class Event : UiEvent {
        data object OnFetchHomeInfo : Event()
    }

    sealed interface SideEffect : UiSideEffect
}
