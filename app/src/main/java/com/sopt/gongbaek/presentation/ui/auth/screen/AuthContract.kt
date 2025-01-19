package com.sopt.gongbaek.presentation.ui.auth.screen

import com.sopt.gongbaek.domain.model.TimeTable
import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState

class AuthContract {
    data class State(
        val userInfo: UserInfo = UserInfo()
    ) : UiState

    sealed class Event : UiEvent {
        data class OnProfileImageSelected(val profileImage: Int) : Event()
        data class OnNicknameChanged(val nickname: String) : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateBack : SideEffect
        data object NavigateNickname : SideEffect
    }
}
