package com.sopt.gongbaek.presentation.ui.auth.screen

import com.sopt.gongbaek.domain.model.Majors
import com.sopt.gongbaek.domain.model.Universities
import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState

class AuthContract {
    data class State(
        val userInfo: UserInfo = UserInfo(),
        val universities: Universities = Universities(),
        val majors: Majors = Majors(),
        val univ: String = "",
        val enterMajor: String = "",
        val selectedGrade: String = "",
        val mbti: String = "",
        val selectedGender: String = "",
        val selectedTimeSlotsByDay: Map<String, List<Int>> = emptyMap()
    ) : UiState

    sealed class Event : UiEvent {
        data class OnProfileImageSelected(val profileImage: Int) : Event()
        data class OnNicknameChanged(val nickname: String) : Event()
        data class OnSearchUnivChanged(val univ: String) : Event()
        data object OnUnivSearchClick : Event()
        data class OnUnivSelected(val school: String) : Event()

        data class OnMajorSelected(val selectedMajor: String) : Event()
        data class OnMajorSearchChanged(val enterMajor: String) : Event()
        data object OnMajorSearchClick : Event()

        data class OnGradeSelected(val selectedGrade: String) : Event()
        data class OnYearSelected(val year: Int) : Event()

        data class OnGenderSelected(val selectedGender: String) : Event()

        data class OnSelfIntroductionChanged(val selfIntroduction: String) : Event()

        data class OnTimeSlotSelectionChange(val day: String, val timeSlots: List<Int>) : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateBack : SideEffect
        data object NavigateNickname : SideEffect
        data object NavigateUnivMajor : SideEffect
        data object NavigateUnivSearch : SideEffect
        data object NavigateMajorSearch : SideEffect
        data object NavigateSelfIntroduction : SideEffect
        data object NavigateEnterTimetable : SideEffect
        data object NavigateGapTimetable : SideEffect
        data object NavigateCompleteAuth : SideEffect
        data object NavigateHome : SideEffect
    }
}
