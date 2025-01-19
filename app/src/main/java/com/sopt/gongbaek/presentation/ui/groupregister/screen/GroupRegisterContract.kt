package com.sopt.gongbaek.presentation.ui.groupregister.screen

import com.sopt.gongbaek.domain.model.GroupRegisterInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState

class GroupRegisterContract {
    data class State(
        val groupRegisterInfo: GroupRegisterInfo = GroupRegisterInfo(
            groupType = "",
            weekDate = "",
            weekDay = "",
            startTime = 0.0,
            endTime = 0.0,
            dueDate = "",
            category = "",
            coverImg = 0,
            location = "",
            maxPeopleCount = 0,
            groupTitle = "",
            introduction = ""
        )
    ) : UiState

    sealed class Event : UiEvent

    sealed interface SideEffect : UiSideEffect
}
