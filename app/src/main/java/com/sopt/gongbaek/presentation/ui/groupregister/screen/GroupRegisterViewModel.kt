package com.sopt.gongbaek.presentation.ui.groupregister.screen

import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupRegisterViewModel @Inject constructor() :
    BaseViewModel<GroupRegisterContract.State, GroupRegisterContract.Event, GroupRegisterContract.SideEffect>() {
    override fun createInitialState(): GroupRegisterContract.State = GroupRegisterContract.State()

    override suspend fun handleEvent(event: GroupRegisterContract.Event) {
    }
}
