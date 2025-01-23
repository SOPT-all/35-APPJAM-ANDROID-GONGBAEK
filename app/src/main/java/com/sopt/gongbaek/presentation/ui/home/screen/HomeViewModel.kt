package com.sopt.gongbaek.presentation.ui.home.screen

import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.domain.usecase.FetchHomeScreenUseCase
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchHomeScreenUseCase: FetchHomeScreenUseCase
) : BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.SideEffect>() {

    override fun createInitialState(): HomeContract.State = HomeContract.State()

    override suspend fun handleEvent(event: HomeContract.Event) {
        when (event) {
            HomeContract.Event.OnFetchHomeInfo -> fetchHomeInfo()
        }
    }

    private fun fetchHomeInfo() =
        viewModelScope.launch {
            setState { copy(homeLoadState = UiLoadState.Loading) }
            fetchHomeScreenUseCase()
                .fold(
                    onSuccess = { nearestGroup ->
                        setState { copy(homeLoadState = UiLoadState.Success, nearestGroup = nearestGroup) }
                    },
                    onFailure = { setState { copy(homeLoadState = UiLoadState.Error) } }
                )
        }
}
