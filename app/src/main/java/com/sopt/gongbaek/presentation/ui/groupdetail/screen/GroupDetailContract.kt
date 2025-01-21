package com.sopt.gongbaek.presentation.ui.groupdetail.screen

import com.sopt.gongbaek.domain.model.GroupComments
import com.sopt.gongbaek.domain.model.GroupHost
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState

class GroupDetailContract {
    data class State(
        val loadState: UiLoadState = UiLoadState.Idle,
        val groupInfo: GroupInfo = GroupInfo(),
        val groupHost: GroupHost = GroupHost(),
        val groupComments: GroupComments = GroupComments(),
        val inputComment: String = "",
        val groupApplyState: UiLoadState = UiLoadState.Idle,
        val commentState: UiLoadState = UiLoadState.Idle
    ) : UiState

    sealed class Event : UiEvent {
        data object OnGroupInfoTabClick : Event()
        data object OnApplyClick : Event()
        data object OnDialogConfirmClick : Event()
        data object OnDialogDismissClick : Event()
        data object OnCommentTabClick : Event()
        data class UpdateInputComment(val inputComment: String) : Event()
        data object OnCommentRefreshClick : Event()
        data object OnCommentPostClick : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateBack : SideEffect
        data object NavigateGroupRoom : SideEffect
    }
}
