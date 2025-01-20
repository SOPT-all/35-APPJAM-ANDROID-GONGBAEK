package com.sopt.gongbaek.presentation.ui.groupregister.screen

import com.sopt.gongbaek.domain.model.GroupRegisterInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState
import java.time.LocalDate

class GroupRegisterContract {
    data class State(
        val groupRegisterInfo: GroupRegisterInfo = GroupRegisterInfo()
    ) : UiState

    sealed class Event : UiEvent {
        data class OnGroupCycleSelected(val groupType: String) : Event()
        data class OnDayOfWeekSelected(val weekDay: String) : Event()
        data class OnWeekDateSelected(val weekDate: LocalDate) : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateBack : SideEffect

        data object NavigateDay : SideEffect
        data object NavigateDayOfWeek : SideEffect

        data object NavigateGroupTime : SideEffect
    }
}
