package com.sopt.gongbaek.presentation.ui.grouproom.screen

import com.sopt.gongbaek.domain.model.GroupComments
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.model.GroupPeople
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState

class GroupRoomContract {
    data class State(
        val loadState: UiLoadState = UiLoadState.Idle,
        val groupInfo: GroupInfo = GroupInfo(),
        val groupPeople: GroupPeople = GroupPeople(),
        val groupComments: GroupComments = GroupComments(),
        val inputComment: String = "",
        val commentState: UiLoadState = UiLoadState.Idle
    ) : UiState

    sealed class Event : UiEvent {
        data class UpdateInputComment(val inputComment: String) : Event()
        data object OnCommentRefreshClick : Event()
        data object OnCommentPostClick : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateMyGroup : SideEffect
    }
}
