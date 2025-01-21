package com.sopt.gongbaek.presentation.ui.groupregister.screen

import com.sopt.gongbaek.domain.model.GroupRegisterInfo
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
        }
    }

    fun sendSideEffect(sideEffect: GroupRegisterContract.SideEffect) = setSideEffect(sideEffect)

    private fun updateGroupRegisterInfo(update: GroupRegisterInfo.() -> GroupRegisterInfo) =
        setState { copy(groupRegisterInfo = groupRegisterInfo.update()) }
}
