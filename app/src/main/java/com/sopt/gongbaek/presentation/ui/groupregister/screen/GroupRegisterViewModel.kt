package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.domain.model.GroupRegisterInfo
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupRegisterViewModel @Inject constructor() :
    BaseViewModel<GroupRegisterContract.State, GroupRegisterContract.Event, GroupRegisterContract.SideEffect>() {
    override fun createInitialState(): GroupRegisterContract.State = GroupRegisterContract.State()

    override suspend fun handleEvent(event: GroupRegisterContract.Event) {
        when (event) {
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
                updateGroupRegisterInfo { copy(coverImg = event.cover + 1) }
                setState { copy(selectedCover = event.cover) }
            }

            is GroupRegisterContract.Event.OnPlaceChanged -> {
                updateGroupRegisterInfo { copy(location = event.place) }
            }

            is GroupRegisterContract.Event.OnPeopleChanged -> {
                updateGroupRegisterInfo { copy(maxPeopleCount = event.peopleCount) }
            }

            is GroupRegisterContract.Event.OnTitleChanged -> {
                updateGroupRegisterInfo { copy(groupTitle = event.title) }
            }

            is GroupRegisterContract.Event.OnIntroductionChanged -> {
                updateGroupRegisterInfo { copy(introduction = event.introduction) }
            }

            is GroupRegisterContract.Event.OnRegisterButtonClicked -> {
                registerGroup()
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

                updateGroupRegisterTime(event.day, event.timeSlots)
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

    private fun updateGroupRegisterTime(day: String, timeSlots: List<Int>) {
        if (timeSlots.isNotEmpty()) {
            val startTime = timeSlots.minOrNull()?.toDouble() ?: 0.0
            val endTime = timeSlots.maxOrNull()?.toDouble() ?: 0.0

            updateGroupRegisterInfo {
                copy(startTime = startTime, endTime = endTime)
            }
        } else {
            updateGroupRegisterInfo {
                copy(startTime = 0.0, endTime = 0.0)
            }
        }
    }

    private fun registerGroup() {
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
        }
    }

    fun sendSideEffect(sideEffect: GroupRegisterContract.SideEffect) = setSideEffect(sideEffect)

    private fun updateGroupRegisterInfo(update: GroupRegisterInfo.() -> GroupRegisterInfo) =
        setState { copy(groupRegisterInfo = groupRegisterInfo.update()) }
}
