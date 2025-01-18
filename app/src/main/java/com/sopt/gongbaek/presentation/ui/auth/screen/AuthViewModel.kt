package com.sopt.gongbaek.presentation.ui.auth.screen

import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(

) : BaseViewModel<AuthContract.State, AuthContract.Event, AuthContract.SideEffect>() {

    override fun createInitialState(): AuthContract.State = AuthContract.State()

    override suspend fun handleEvent(event: AuthContract.Event) {
        when (event) {
            is AuthContract.Event.OnProfileImageChanged -> updateUserInfo { copy(profileImage = event.profileImage) }
            is AuthContract.Event.OnNicknameChanged -> updateUserInfo { copy(nickname = event.nickname) }
        }
    }

    fun sendSideEffect(sideEffect: AuthContract.SideEffect) =
        setSideEffect(sideEffect)

    private fun updateUserInfo(update: UserInfo.() -> UserInfo) =
        setState { copy(userInfo = userInfo.update()) }

}
