package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.domain.model.GroupRegisterInfo
import com.sopt.gongbaek.domain.type.DayOfWeekType
import com.sopt.gongbaek.domain.usecase.GetLectureTimetableUseCase
import com.sopt.gongbaek.domain.usecase.PostGroupUseCase
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.extension.hasCompleteKoreanCharacters
import com.sopt.gongbaek.presentation.util.extension.isCompleteKorean
import com.sopt.gongbaek.presentation.util.extension.isKoreanChar
import com.sopt.gongbaek.presentation.util.timetable.convertToTimeTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupRegisterViewModel @Inject constructor(
    private val postGroupUseCase: PostGroupUseCase,
    private val getLectureTimetableUseCase: GetLectureTimetableUseCase
) :
    BaseViewModel<GroupRegisterContract.State, GroupRegisterContract.Event, GroupRegisterContract.SideEffect>() {
    override fun createInitialState(): GroupRegisterContract.State = GroupRegisterContract.State(
        registerState = UiLoadState.Idle
    )

    override suspend fun handleEvent(event: GroupRegisterContract.Event) {
        when (event) {
            is GroupRegisterContract.Event.GetLectureTime -> getLectureTime()

            is GroupRegisterContract.Event.OnGroupCycleSelected -> {
                updateGroupRegisterInfo { copy(groupType = setGroupType(event.groupType)) }
                setState { copy(selectedGroupType = event.groupType) }
            }

            is GroupRegisterContract.Event.OnDayOfWeekSelected -> {
                updateGroupRegisterInfo { copy(weekDay = setWeekDay(event.weekDay)) }
                setState { copy(selectedDayOfWeek = event.weekDay) }
            }

            is GroupRegisterContract.Event.OnWeekDateAndDaySelected -> {
                updateGroupRegisterInfo {
                    copy(
                        weekDate = event.weekDate.toString(),
                        weekDay = event.weekDay
                    )
                }
            }

            is GroupRegisterContract.Event.OnCategorySelected -> {
                updateGroupRegisterInfo { copy(category = setCategory(event.category)) }
                setState { copy(selectedCategory = event.category) }
            }

            is GroupRegisterContract.Event.OnCoverSelected -> {
                updateGroupRegisterInfo { copy(coverImg = event.cover) }
                setState { copy(selectedCover = event.cover) }
            }

            is GroupRegisterContract.Event.OnPlaceChanged -> {
                val isValidPlace = event.place.hasCompleteKoreanCharacters(2)
                val containsIncompleteKorean = event.place.any { it.isKoreanChar() && !it.isCompleteKorean() }
                updateGroupRegisterInfo { copy(location = event.place) }
                setState {
                    copy(
                        placeValidation = isValidPlace && !containsIncompleteKorean,
                        placeErrorMessage = when {
                            containsIncompleteKorean -> "완성되지 않은 글자가 포함되어 있습니다."
                            else -> null
                        }
                    )
                }
            }

            is GroupRegisterContract.Event.OnPeopleChanged -> {
                updateGroupRegisterInfo { copy(maxPeopleCount = event.peopleCount) }
            }

            is GroupRegisterContract.Event.OnTitleChanged -> {
                val isValidPlace = event.title.hasCompleteKoreanCharacters(2)
                val containsIncompleteKorean = event.title.any { it.isKoreanChar() && !it.isCompleteKorean() }
                updateGroupRegisterInfo { copy(groupTitle = event.title) }
                setState {
                    copy(
                        titleValidation = isValidPlace && !containsIncompleteKorean,
                        titleErrorMessage = when {
                            containsIncompleteKorean -> "완성되지 않은 글자가 포함되어 있습니다."
                            else -> null
                        }
                    )
                }
            }

            is GroupRegisterContract.Event.OnIntroductionChanged -> {
                updateGroupRegisterInfo { copy(introduction = event.introduction) }
            }

            is GroupRegisterContract.Event.OnRegisterButtonClicked -> {
                registerGroup(groupRegisterInfo = event.groupRegisterInfo)
            }

            is GroupRegisterContract.Event.OnDialogConfirmClicked -> {
                setSideEffect(GroupRegisterContract.SideEffect.NavigateMyGroup)
            }

            is GroupRegisterContract.Event.OnDialogDismissClicked -> {
                setState { copy(registerState = UiLoadState.Idle) }
            }

            is GroupRegisterContract.Event.OnTimeSlotSelected -> {
                val updatedTimeSlots = currentState.selectedTimeSlotsByDay.toMutableMap().apply {
                    this[event.day] = event.timeSlots
                }
                setState { copy(selectedTimeSlotsByDay = updatedTimeSlots) }
                updateGroupRegisterTime(updatedTimeSlots)
            }

            is GroupRegisterContract.Event.OnTimeSlotDeleted -> {
                updateGroupRegisterInfo { copy(startTime = 0.0, endTime = 0.0) }
                setState { copy(selectedTimeSlotsByDay = emptyMap()) }
            }

            is GroupRegisterContract.Event.OnGroupCycleDeleted -> {
                updateGroupRegisterInfo { copy(groupType = "") }
                setState { copy(selectedGroupType = "") }
            }

            is GroupRegisterContract.Event.OnWeekDateAndDayDeleted -> {
                updateGroupRegisterInfo { copy(weekDay = "", weekDate = "") }
                setState { copy(selectedDayOfWeek = "") }
            }

            is GroupRegisterContract.Event.OnDayOfWeekDeleted -> {
                updateGroupRegisterInfo { copy(weekDay = "") }
                setState { copy(selectedDayOfWeek = "") }
            }

            is GroupRegisterContract.Event.OnCategoryDeleted -> {
                updateGroupRegisterInfo { copy(category = "") }
                setState { copy(selectedCategory = "") }
            }

            is GroupRegisterContract.Event.OnCoverDeleted -> {
                updateGroupRegisterInfo { copy(coverImg = 0) }
                setState { copy(selectedCover = null) }
            }

            is GroupRegisterContract.Event.OnPlacePeopleDeleted -> {
                updateGroupRegisterInfo { copy(location = "", maxPeopleCount = 2) }
            }

            is GroupRegisterContract.Event.OnTitleIntroductionDeleted -> {
                updateGroupRegisterInfo { copy(groupTitle = "", introduction = "") }
            }
        }
    }

    private fun updateGroupRegisterTime(timeSlots: Map<String, List<Int>>) {
        if (timeSlots.isNotEmpty()) {
            val lectureTimeTable = convertToTimeTable(timeSlots)
            val startTime = lectureTimeTable.first().startTime
            val endTime = lectureTimeTable.first().endTime

            updateGroupRegisterInfo {
                copy(startTime = startTime, endTime = endTime)
            }
        } else {
            updateGroupRegisterInfo {
                copy(startTime = 0.0, endTime = 0.0)
            }
        }
    }

    private fun registerGroup(groupRegisterInfo: GroupRegisterInfo) {
        viewModelScope.launch {
            setState { copy(registerState = UiLoadState.Loading) }

            runCatching {
                postGroupUseCase(groupRegisterInfo = groupRegisterInfo).fold(
                    onSuccess = {
                        setState { copy(registerState = UiLoadState.Success) }
                    },
                    onFailure = { error ->
                        setState { copy(registerState = UiLoadState.Error) }
                    }
                )
            }
        }
    }

    private fun getLectureTime() {
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }

            runCatching {
                getLectureTimetableUseCase()
            }.onSuccess { timeTable ->
                val transformedTimeTable = timeTable.mapKeys { dayofweek ->
                    DayOfWeekType.toDayOfWeekRemoveSuffix(dayofweek.key)
                }

                setState {
                    copy(
                        lectureTime = transformedTimeTable,
                        loadState = UiLoadState.Success
                    )
                }
            }.onFailure {
                setState { copy(loadState = UiLoadState.Error) }
            }
        }
    }

    fun sendSideEffect(sideEffect: GroupRegisterContract.SideEffect) = setSideEffect(sideEffect)

    private fun updateGroupRegisterInfo(update: GroupRegisterInfo.() -> GroupRegisterInfo) =
        setState { copy(groupRegisterInfo = groupRegisterInfo.update()) }
}
