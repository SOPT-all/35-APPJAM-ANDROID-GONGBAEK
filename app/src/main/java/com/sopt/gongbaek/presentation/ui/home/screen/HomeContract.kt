package com.sopt.gongbaek.presentation.ui.home.screen

import com.sopt.gongbaek.domain.model.NearestGroup
import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState
import com.sopt.gongbaek.presentation.util.timetable.nearestGroupFormatSchedule

class HomeContract {

    data class State(
        val userInfo: UserInfo = UserInfo(),
        val nearestGroup: NearestGroup = NearestGroup(),
        val nearestGroupSchedule: String = nearestGroupFormatSchedule(
            nearestGroup.weekDate,
            nearestGroup.startTime,
            nearestGroup.endTime
        ),
    ) : UiState

    sealed class Event : UiEvent {}

    sealed interface SideEffect : UiSideEffect {}

}
