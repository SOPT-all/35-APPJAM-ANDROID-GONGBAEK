package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.domain.type.GenderType
import com.sopt.gongbaek.domain.type.GradeType
import com.sopt.gongbaek.domain.usecase.GetSearchMajorsResultUseCase
import com.sopt.gongbaek.domain.usecase.GetSearchUniversitiesResultUseCase
import com.sopt.gongbaek.domain.usecase.RegisterUserInfoUseCase
import com.sopt.gongbaek.domain.usecase.SetTokenUseCase
import com.sopt.gongbaek.domain.usecase.ValidateNicknameUseCase
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.extension.createMbti
import com.sopt.gongbaek.presentation.util.extension.isKoreanChar
import com.sopt.gongbaek.presentation.util.timetable.convertToTimeTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getSearchUniversitiesResultUseCase: GetSearchUniversitiesResultUseCase,
    private val getSearchMajorsResultUseCase: GetSearchMajorsResultUseCase,
    private val registerUserInfoUseCase: RegisterUserInfoUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val validateNicknameUseCase: ValidateNicknameUseCase
) : BaseViewModel<AuthContract.State, AuthContract.Event, AuthContract.SideEffect>() {

    override fun createInitialState(): AuthContract.State = AuthContract.State()

    override suspend fun handleEvent(event: AuthContract.Event) {
        when (event) {
            is AuthContract.Event.OnProfileImageSelected -> updateUserInfo { copy(profileImage = event.profileImage) }
            is AuthContract.Event.OnNicknameChanged -> {
                val filteredNickname = event.nickname.filter { it.isKoreanChar() }
                updateUserInfo { copy(nickname = filteredNickname) }
                setState {
                    copy(
                        nicknameValidation = true,
                        nicknameErrorMessage = null
                    )
                }
            }

            is AuthContract.Event.OnSearchUnivChanged -> setState { copy(univ = event.univ) }
            is AuthContract.Event.OnUnivSearchClick -> {
                fetchUnivSearchResult()
            }

            is AuthContract.Event.OnUnivSelected -> {
                val updatedSchool = toggleSelection(currentState.userInfo.school, event.school)
                setState {
                    copy(
                        userInfo = currentState.userInfo.copy(school = updatedSchool),
                        univSearchSelectedItem = updatedSchool
                    )
                }
            }

            is AuthContract.Event.OnMajorSearchChanged -> setState { copy(enterMajor = event.enterMajor) }

            is AuthContract.Event.OnMajorSelected -> {
                val updatedMajor = toggleSelection(currentState.userInfo.major, event.selectedMajor)
                setState {
                    copy(
                        userInfo = currentState.userInfo.copy(major = updatedMajor),
                        majorSearchSelectedItem = updatedMajor
                    )
                }
            }

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

            is AuthContract.Event.SubmitUserInfo -> submitUserInfo()

            is AuthContract.Event.ValidateNickname -> {
                validateNickname(currentState.userInfo.nickname) { isValid ->
                    if (isValid) {
                        sendSideEffect(AuthContract.SideEffect.NavigateUnivMajor)
                    }
                }
            }
        }
    }

    fun sendSideEffect(sideEffect: AuthContract.SideEffect) =
        setSideEffect(sideEffect)

    private fun toggleSelection(current: String, newSelection: String): String =
        if (current == newSelection) "" else newSelection

    private fun updateMbti() {
        val mbti = createMbti(
            firstLetter = currentState.energyDirectionOptions.takeIf { it.isNotBlank() } ?: return,
            secondLetterType = currentState.informationGatheringOptions.takeIf { it.isNotBlank() } ?: return,
            thirdLetterType = currentState.decisionMakingOptions.takeIf { it.isNotBlank() } ?: return,
            fourthLetterType = currentState.lifestyleOrientationOptions.takeIf { it.isNotBlank() } ?: return
        )
        updateUserInfo { copy(mbti = mbti) }
    }

    private fun fetchUnivSearchResult() =
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
            getSearchUniversitiesResultUseCase(currentState.univ).fold(
                onSuccess = { universities ->
                    setState { copy(universities = universities) }
                },
                onFailure = {
                    setState { copy(loadState = UiLoadState.Error) }
                }
            )
        }

    private fun fetchMajorSearch() =
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
            getSearchMajorsResultUseCase(
                universityName = currentState.userInfo.school,
                majorName = currentState.enterMajor
            ).fold(
                onSuccess = { majors ->
                    setState { copy(majors = majors) }
                },
                onFailure = {
                    setState { copy(loadState = UiLoadState.Error) }
                }
            )
        }

    private fun submitUserInfo() =
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
            registerUserInfoUseCase(currentState.userInfo).fold(
                onSuccess = { userAuth ->
                    setState { copy(loadState = UiLoadState.Success) }
                    setSideEffect(AuthContract.SideEffect.NavigateCompleteAuth)
//                    setTokenUseCase(userAuth.accessToken, userAuth.refreshToken)
                },
                onFailure = {
                    setState { copy(loadState = UiLoadState.Error) }
                }
            )
        }

    private fun validateNickname(nickname: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = validateNicknameUseCase(nickname)
            result.fold(
                onSuccess = {
                    setState {
                        copy(
                            nicknameValidation = true,
                            nicknameErrorMessage = null
                        )
                    }
                    onResult(true)
                },
                onFailure = { exception ->
                    if (exception.message == ERROR_CODE_DUPLICATE_NICKNAME) {
                        setState {
                            copy(
                                nicknameValidation = false,
                                nicknameErrorMessage = NICKNAME_VALIDATION_ERROR_MESSAGE
                            )
                        }
                    }
                    onResult(false)
                }
            )
        }
    }

    private fun updateUserInfo(update: UserInfo.() -> UserInfo) =
        setState { copy(userInfo = userInfo.update()) }

    companion object {
        private const val NICKNAME_VALIDATION_ERROR_MESSAGE = "중복된 닉네임입니다. 다시 입력해주세요."
        private const val ERROR_CODE_DUPLICATE_NICKNAME = "HTTP 409 "
    }
}
