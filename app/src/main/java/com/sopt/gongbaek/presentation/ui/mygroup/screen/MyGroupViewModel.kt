package com.sopt.gongbaek.presentation.ui.mygroup.screen

import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyGroupViewModel @Inject constructor() : BaseViewModel<MyGroupContract.State, MyGroupContract.Event, MyGroupContract.SideEffect>() {
    override fun createInitialState(): MyGroupContract.State = MyGroupContract.State()

    override suspend fun handleEvent(event: MyGroupContract.Event) {
        when (event) {
            is MyGroupContract.Event.GetRegisterGroups -> {
                getRegisterGroups()
            }
            is MyGroupContract.Event.GetApplyGroups -> {
                getApplyGroups()
            }
        }
    }

    fun sendSideEffect(sideEffect: MyGroupContract.SideEffect) = setSideEffect(sideEffect)

    private fun getRegisterGroups() {
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
            // 내가 모집한 모임들을 불러오는 로직
        }
    }

    private fun getApplyGroups() {
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
            // 내가 신청한 모임들을 불러오는 로직
        }
    }
}
