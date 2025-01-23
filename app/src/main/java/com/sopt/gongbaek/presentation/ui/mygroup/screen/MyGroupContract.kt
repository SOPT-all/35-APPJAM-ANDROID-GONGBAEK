package com.sopt.gongbaek.presentation.ui.mygroup.screen

import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState

class MyGroupContract {
    data class State(
        val registerGroupsLoadState: UiLoadState = UiLoadState.Idle,
        val registerActiveGroups: List<GroupInfo> = listOf(),
        val registerClosedGroups: List<GroupInfo> = listOf(),
        val applyGroupsLoadState: UiLoadState = UiLoadState.Idle,
        val applyActiveGroups: List<GroupInfo> = listOf(),
        val applyClosedGroups: List<GroupInfo> = listOf()
    ) : UiState

    sealed class Event : UiEvent {
        data object OnRegisterGroupsTabClick : Event()
        data object OnApplyGroupsTabClick : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data class NavigateGroupDetail(val groupId: Int, val groupCycle: String) : SideEffect
        data class NavigateGroupRoom(val groupId: Int, val groupCycle: String) : SideEffect
    }
}
