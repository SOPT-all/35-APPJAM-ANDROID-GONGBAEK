package com.sopt.gongbaek.presentation.ui.home.screen

import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.SideEffect>() {

    override fun createInitialState(): HomeContract.State = HomeContract.State()

    override suspend fun handleEvent(event: HomeContract.Event) {
        TODO("Not yet implemented")
    }
}
