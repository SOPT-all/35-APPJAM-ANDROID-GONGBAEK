package com.sopt.gongbaek.presentation.ui.home.screen

import com.sopt.gongbaek.presentation.util.base.UiEvent
import com.sopt.gongbaek.presentation.util.base.UiSideEffect
import com.sopt.gongbaek.presentation.util.base.UiState

class HomeContract {

    data class State(
        val TODO: String = "Not yet implemented"
    ) : UiState

    sealed class Event : UiEvent {}

    sealed interface SideEffect : UiSideEffect {}

}
