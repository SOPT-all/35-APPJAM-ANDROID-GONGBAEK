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
            }

            is GroupRegisterContract.Event.OnDayOfWeekSelected -> {
                updateGroupRegisterInfo { copy(weekDay = setWeekDay(event.weekDay)) }
            }

            is GroupRegisterContract.Event.OnWeekDateAndDaySelected -> {
                updateGroupRegisterInfo {
                    copy(
                        weekDate = event.weekDate.toString(),
                        weekDay = event.weekDay
                    )
                }
            }
        }
    }

    fun sendSideEffect(sideEffect: GroupRegisterContract.SideEffect) = setSideEffect(sideEffect)

    private fun updateGroupRegisterInfo(update: GroupRegisterInfo.() -> GroupRegisterInfo) =
        setState { copy(groupRegisterInfo = groupRegisterInfo.update()) }
}
