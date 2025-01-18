package com.sopt.gongbaek.presentation.ui.auth.screen

import com.sopt.gongbaek.domain.model.TimeTable
import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState

class AuthContract {

    data class State(
        val userInfo: UserInfo = UserInfo(
            profileImage = null,
            nickname = "",
            mbti = "",
            schoolName = "",
            schoolMajor = "",
            schoolGrade = null,
            enterYear = null,
            introduction = "",
            sex = "",
            timeTable = TimeTable(
                weekDay = "",
                startTime = 0f,
                endTime = 0f
            )
        )
    ) : UiState

    sealed class Event : UiEvent {
        data class OnProfileImageChanged(val profileImage: Int?) : Event()
        data class OnNicknameChanged(val nickname: String) : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToBack : SideEffect
        data object NavigateToNickname : SideEffect
    }
}
