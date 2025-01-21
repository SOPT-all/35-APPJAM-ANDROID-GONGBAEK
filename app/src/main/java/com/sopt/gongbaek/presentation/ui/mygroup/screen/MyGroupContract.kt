package com.sopt.gongbaek.presentation.ui.mygroup.screen

import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState

class MyGroupContract {
    data class State(
        val loadState: UiLoadState = UiLoadState.Idle,
        val activeGroups: List<GroupInfo> = listOf(),
        val closedGroups: List<GroupInfo> = listOf()
    ) : UiState

    sealed class Event : UiEvent {
        data object GetRegisterGroups : Event()
        data object GetApplyGroups : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateGroupDetail : SideEffect
        data object NavigateGroupRoom : SideEffect
    }
}
