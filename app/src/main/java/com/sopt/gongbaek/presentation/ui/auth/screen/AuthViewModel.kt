package com.sopt.gongbaek.presentation.ui.auth.screen

import com.sopt.gongbaek.domain.model.Majors
import com.sopt.gongbaek.domain.model.Universities
import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.domain.type.GenderType
import com.sopt.gongbaek.domain.type.GradeType
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.extension.createMbti
import com.sopt.gongbaek.presentation.util.timetable.convertToTimeTable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : BaseViewModel<AuthContract.State, AuthContract.Event, AuthContract.SideEffect>() {

    override fun createInitialState(): AuthContract.State = AuthContract.State()

    override suspend fun handleEvent(event: AuthContract.Event) {
        when (event) {
            is AuthContract.Event.OnProfileImageSelected -> updateUserInfo { copy(profileImage = event.profileImage) }
            is AuthContract.Event.OnNicknameChanged -> updateUserInfo { copy(nickname = event.nickname) }
            is AuthContract.Event.OnSearchUnivChanged -> setState { copy(univ = event.univ) }
            is AuthContract.Event.OnUnivSearchClick -> {
                fetchUnivSearch()
            }

            is AuthContract.Event.OnUnivSelected -> updateUserInfo { copy(school = event.school) }
            is AuthContract.Event.OnMajorSearchChanged -> setState { copy(enterMajor = event.enterMajor) }
            is AuthContract.Event.OnMajorSelected -> updateUserInfo { copy(major = event.selectedMajor) }
            is AuthContract.Event.OnMajorSearchClick -> {
                fetchMajorSearch()
            }

            is AuthContract.Event.OnGradeSelected -> {
                val grade = GradeType.toGrade(event.selectedGrade)
                updateUserInfo { copy(grade = grade) }
                setState { copy(selectedGrade = event.selectedGrade) }
            }

            is AuthContract.Event.OnYearSelected -> updateUserInfo { copy(enterYear = event.year) }
            is AuthContract.Event.OnGenderSelected -> {
                val gender = GenderType.toGender(event.selectedGender)
                updateUserInfo { copy(gender = gender) }
                setState { copy(selectedGender = event.selectedGender) }
            }

            is AuthContract.Event.OnEnergyDirectionOptionSelected -> {
                setState { copy(energyDirectionOptions = event.option) }
                updateMbti()
            }

            is AuthContract.Event.OnInformationGatheringOptionSelected -> {
                setState { copy(informationGatheringOptions = event.option) }
                updateMbti()
            }

            is AuthContract.Event.OnDecisionMakingOptionSelected -> {
                setState { copy(decisionMakingOptions = event.option) }
                updateMbti()
            }

            is AuthContract.Event.OnLifestyleOrientationOptionSelected -> {
                setState { copy(lifestyleOrientationOptions = event.option) }
                updateMbti()
            }

            is AuthContract.Event.OnSelfIntroductionChanged -> updateUserInfo { copy(introduction = event.selfIntroduction) }
            is AuthContract.Event.OnTimeSlotSelectionChange -> {
                val timeSlotsByDay = currentState.selectedTimeSlotsByDay.toMutableMap()
                timeSlotsByDay[event.day] = event.timeSlots
                setState { copy(selectedTimeSlotsByDay = timeSlotsByDay) }
                updateUserInfo { copy(timeTable = convertToTimeTable(timeSlotsByDay)) }
            }
        }
    }

    private fun updateMbti() {
        val mbti = createMbti(
            firstLetter = currentState.energyDirectionOptions.takeIf { it.isNotBlank() } ?: return,
            secondLetterType = currentState.informationGatheringOptions.takeIf { it.isNotBlank() } ?: return,
            thirdLetterType = currentState.decisionMakingOptions.takeIf { it.isNotBlank() } ?: return,
            fourthLetterType = currentState.lifestyleOrientationOptions.takeIf { it.isNotBlank() } ?: return
        )
        updateUserInfo { copy(mbti = mbti) }
    }

    fun sendSideEffect(sideEffect: AuthContract.SideEffect) =
        setSideEffect(sideEffect)

    private fun fetchUnivSearch() {
//        viewModelScope.launch {
//            currentState.univ
        val universities = listOf(
            "한양대학교",
            "건국대학교 서울캠퍼스",
            "서울대학교",
            "건국대학교 서울캠퍼스",
            "고려대학교",
            "건국대학교 서울캠퍼스",
            "연세대학교",
            "한양대학교",
            "건국대학교 서울캠퍼스",
            "서울대학교",
            "건국대학교 서울캠퍼스",
            "고려대학교",
            "건국대학교 서울캠퍼스",
            "연세대학교"
        )
        setState { copy(universities = Universities(universities)) }
    }

    private fun fetchMajorSearch() {
//        viewModelScope.launch {
//            currentState.major
        val majors = listOf(
            "컴퓨터공학과",
            "건축학과",
            "경영학과",
            "컴퓨터공학과",
            "건축학과",
            "경영학과",
            "컴퓨터공학과",
            "건축학과",
            "경��학과",
            "컴퓨터공학과",
            "건축학과",
            "경영학과",
            "컴퓨터공학과",
            "건축학과",
            "경영학과"
        )
        setState { copy(majors = Majors(majors)) }
    }

    private fun updateUserInfo(update: UserInfo.() -> UserInfo) =
        setState { copy(userInfo = userInfo.update()) }
}
