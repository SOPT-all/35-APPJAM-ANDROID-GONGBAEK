package com.sopt.gongbaek.presentation.ui.grouplist.screen

import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState

class GroupListContract {
    data class State(
        val loadState: UiLoadState = UiLoadState.Idle,
        val selectedDayOfWeekIndex: Int = 0,
        val selectedCategoryIndex: Int = 0,
        val toggleCheckedState: Boolean = true,
        val groups: List<GroupInfo> = listOf()
    ) : UiState

    sealed class Event : UiEvent {
        data class GetGroups(val category: String) : Event()
        data class OnDayOfWeekSelected(val index: Int) : Event()
        data class OnCategorySelected(val index: Int) : Event()
        data class OnToggleSelected(val state: Boolean) : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data class NavigateGroupDetail(val groupId: Int, val groupCycle: String) : SideEffect
        data object NavigateGroupRegister : SideEffect
    }
}