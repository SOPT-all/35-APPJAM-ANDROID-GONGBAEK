package com.sopt.gongbaek.presentation.ui.groupregister.screen

import com.sopt.gongbaek.domain.model.GroupRegisterInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState
import java.time.LocalDate

class GroupRegisterContract {
    data class State(
        val loadState: UiLoadState = UiLoadState.Idle,
        val groupRegisterInfo: GroupRegisterInfo = GroupRegisterInfo(),
        val selectedGroupType: String = "",
        val selectedDayOfWeek: String = "",
        val selectedCategory: String = "",
        val selectedCover: Int? = null,
        val registerState: UiLoadState = UiLoadState.Idle,
        val lectureTime: Map<String, List<Int>> = mapOf(
            "월" to listOf(0, 1, 2, 3, 4, 5, 6),
            "화" to listOf(7, 8, 9, 10),
            "수" to listOf(0, 1, 2, 3, 4, 5, 6),
            "목" to listOf(3, 4, 5, 6, 7),
            "금" to listOf(0, 1, 2, 3, 9, 10, 11, 12)
        ),
        val selectedTimeSlotsByDay: Map<String, List<Int>> = emptyMap()
    ) : UiState

    sealed class Event : UiEvent {
        data class OnGroupCycleSelected(val groupType: String) : Event()
        data class OnDayOfWeekSelected(val weekDay: String) : Event()
        data class OnWeekDateAndDaySelected(val weekDate: LocalDate, val weekDay: String) : Event()
        data class OnCategorySelected(val category: String) : Event()
        data class OnCoverSelected(val cover: Int) : Event()
        data class OnPlaceChanged(val place: String) : Event()
        data class OnPeopleChanged(val peopleCount: Int) : Event()
        data class OnTitleChanged(val title: String) : Event()
        data class OnIntroductionChanged(val introduction: String) : Event()
        data class OnRegisterButtonClicked(val groupRegisterInfo: GroupRegisterInfo) : Event()
        data object OnDialogConfirmClicked : Event()
        data object OnDialogDismissClicked : Event()
        data class OnTimeSlotSelected(val day: String, val timeSlots: List<Int>) : Event()
        data object OnTimeSlotDeleted : Event()
        data object OnGroupCycleDeleted : Event()
        data object OnDayOfWeekDeleted : Event()
        data object OnWeekDateAndDayDeleted : Event()
        data object OnCategoryDeleted : Event()
        data object OnCoverDeleted : Event()
        data object OnPlacePeopleDeleted : Event()
        data object OnTitleIntroductionDeleted : Event()
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
        data object NavigateRegister : SideEffect
        data object NavigateMyGroup : SideEffect
    }
}
