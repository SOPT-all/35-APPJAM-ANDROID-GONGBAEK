package com.sopt.gongbaek.presentation.ui.mygroup.screen

import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.domain.usecase.GetMyGroupsUseCase
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyGroupViewModel @Inject constructor(
    private val getMyGroupsUseCase: GetMyGroupsUseCase
) : BaseViewModel<MyGroupContract.State, MyGroupContract.Event, MyGroupContract.SideEffect>() {

    override fun createInitialState(): MyGroupContract.State = MyGroupContract.State()

    override suspend fun handleEvent(event: MyGroupContract.Event) {
        when (event) {
            is MyGroupContract.Event.OnRegisterGroupsTabClick -> getRegisterGroups()
            is MyGroupContract.Event.OnApplyGroupsTabClick -> getApplyGroups()
        }
    }

    fun sendSideEffect(sideEffect: MyGroupContract.SideEffect) = setSideEffect(sideEffect)

    private fun getRegisterGroups() {
        viewModelScope.launch {
            setState { copy(registerGroupsLoadState = UiLoadState.Loading) }
            getMyGroupsUseCase(category = "REGISTER", status = true).fold(
                onSuccess = { activeGroups ->
                    setState { copy(registerActiveGroups = activeGroups) }
                },
                onFailure = { setState { copy(registerGroupsLoadState = UiLoadState.Error) } }
            )
            getMyGroupsUseCase(category = "REGISTER", status = false).fold(
                onSuccess = { closedGroups ->
                    setState {
                        copy(
                            registerGroupsLoadState = UiLoadState.Success,
                            registerClosedGroups = closedGroups
                        )
                    }
                },
                onFailure = { setState { copy(registerGroupsLoadState = UiLoadState.Error) } }
            )
        }
    }

    private fun getApplyGroups() {
        viewModelScope.launch {
            setState { copy(applyGroupsLoadState = UiLoadState.Loading) }
            getMyGroupsUseCase(category = "APPLY", status = true).fold(
                onSuccess = { activeGroups ->
                    setState { copy(applyActiveGroups = activeGroups) }
                },
                onFailure = { setState { copy(applyGroupsLoadState = UiLoadState.Error) } }
            )
            getMyGroupsUseCase(category = "APPLY", status = false).fold(
                onSuccess = { closedGroups ->
                    setState {
                        copy(
                            applyGroupsLoadState = UiLoadState.Success,
                            applyClosedGroups = closedGroups
                        )
                    }
                },
                onFailure = { setState { copy(applyGroupsLoadState = UiLoadState.Error) } }
            )
        }
    }
}
