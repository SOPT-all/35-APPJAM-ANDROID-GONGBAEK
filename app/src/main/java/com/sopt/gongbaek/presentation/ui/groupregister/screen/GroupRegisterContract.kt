package com.sopt.gongbaek.presentation.ui.groupregister.screen

import com.sopt.gongbaek.domain.model.GroupRegisterInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState
import java.time.LocalDate

class GroupRegisterContract {
    data class State(
        val groupRegisterInfo: GroupRegisterInfo = GroupRegisterInfo(),
        val selectedGroupType: String = "",
        val selectedDayOfWeek: String = "",
        val selectedCategory: String = "",
        val selectedCover: Int? = null
    ) : UiState

    sealed class Event : UiEvent {
        data class OnGroupCycleSelected(val groupType: String) : Event()
        data class OnDayOfWeekSelected(val weekDay: String) : Event()
        data class OnWeekDateAndDaySelected(val weekDate: LocalDate, val weekDay: String) : Event()
        data class OnCategorySelected(val category: String) : Event()
        data class OnCoverSelected(val cover: Int) : Event()
        data class OnPlaceChanged(val place: String) : Event()
        data class OnPeopleChanged(val peopleCount: Int) : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateBack : SideEffect
        data object NavigateDay : SideEffect
        data object NavigateDayOfWeek : SideEffect
        data object NavigateTime : SideEffect
        data object NavigateCategory : SideEffect
        data object NavigateCover : SideEffect
        data object NavigatePlacePeople : SideEffect
        data object NavigateIntroduction : SideEffect
    }
}
