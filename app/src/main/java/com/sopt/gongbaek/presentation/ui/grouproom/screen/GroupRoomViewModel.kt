package com.sopt.gongbaek.presentation.ui.grouproom.screen

import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class GroupRoomViewModel @Inject constructor(
//
//): BaseViewModel<GroupRoomContract.State, GroupRoomContract.Event, GroupRoomContract.SideEffect>() {
//    override fun createInitialState(): GroupRoomContract.State = GroupRoomContract.State()
//
//    override suspend fun handleEvent(event: GroupRoomContract.Event) {
//        TODO("Not yet implemented")
//    }
//
//    fun sendSideEffect(sideEffect: GroupRoomContract.SideEffect) = setSideEffect(sideEffect)
//
//}