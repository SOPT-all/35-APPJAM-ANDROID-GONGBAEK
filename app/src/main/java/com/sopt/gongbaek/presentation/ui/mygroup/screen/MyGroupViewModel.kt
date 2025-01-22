package com.sopt.gongbaek.presentation.ui.mygroup.screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.domain.usecase.GetMyGroupsUseCase
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyGroupViewModel @Inject constructor(
    private val getMyGroupsUseCase: GetMyGroupsUseCase
) : BaseViewModel<MyGroupContract.State, MyGroupContract.Event, MyGroupContract.SideEffect>() {
    override fun createInitialState(): MyGroupContract.State = MyGroupContract.State()

    init {
        getRegisterGroups()
        Timber.tag("MyGroupViewModel Init").d("실행됐지롱 ㅋㅋ")
    }

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
            getMyGroupsUseCase(category = "REGISTER", status = true).fold(
                onSuccess = { activeGroups ->
                    setState {
                        copy(
                            loadState = UiLoadState.Success,
                            activeGroups = activeGroups
                        )
                    }
                    Timber.tag("activeGroups").d("${currentState.activeGroups}")
                },
                onFailure = {
                    setState { copy(loadState = UiLoadState.Error) }
                }
            )
            getMyGroupsUseCase(category = "REGISTER", status = false).fold(
                onSuccess = { closedGroups ->
                    setState {
                        copy(
                            loadState = UiLoadState.Success,
                            closedGroups = closedGroups
                        )
                    }
                },
                onFailure = {
                    setState { copy(loadState = UiLoadState.Error) }
                }
            )
        }
    }

    private fun getApplyGroups() {
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
            getMyGroupsUseCase(category = "APPLY", status = true).fold(
                onSuccess = { activeGroups ->
                    setState {
                        copy(
                            loadState = UiLoadState.Success,
                            activeGroups = activeGroups
                        )
                    }
                },
                onFailure = {
                    setState { copy(loadState = UiLoadState.Error) }
                }
            )
            getMyGroupsUseCase(category = "APPLY", status = false).fold(
                onSuccess = { closedGroups ->
                    setState {
                        copy(
                            loadState = UiLoadState.Success,
                            closedGroups = closedGroups
                        )
                    }
                },
                onFailure = {
                    setState { copy(loadState = UiLoadState.Error) }
                }
            )
        }
    }
}
