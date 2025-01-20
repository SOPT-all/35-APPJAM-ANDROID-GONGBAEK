package com.sopt.gongbaek.presentation.ui.grouproom.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupRoomViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<GroupRoomContract.State, GroupRoomContract.Event, GroupRoomContract.SideEffect>() {

    init {
        val groupId: Int = savedStateHandle["groupId"] ?: 0
        val groupCycle: String = savedStateHandle["groupCycle"] ?: ""
        getGroupRoomInfo(groupId = groupId, groupCycle = groupCycle)
    }

    override fun createInitialState(): GroupRoomContract.State = GroupRoomContract.State()

    override suspend fun handleEvent(event: GroupRoomContract.Event) {
        when (event) {
            is GroupRoomContract.Event.UpdateInputComment -> setState {
                copy(inputComment = event.inputComment)
            }
            is GroupRoomContract.Event.OnCommentRefreshClick -> {
                getGroupComment()
            }
            is GroupRoomContract.Event.OnCommentPostClick -> {
                postInputComment()
            }
        }
    }

    fun sendSideEffect(sideEffect: GroupRoomContract.SideEffect) = setSideEffect(sideEffect)

    private fun getGroupRoomInfo(groupId: Int, groupCycle: String) {
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
            // 모임 정보, 모임 참여자 정보, 댓글 정보 서버 통신 로직
        }
    }

    private fun getGroupComment() {
        viewModelScope.launch {
            setState { copy(commentState = UiLoadState.Loading) }
            // 서버로부터 모임의 댓글 정보를 조회
        }
    }

    private fun postInputComment() {
        viewModelScope.launch {
            setState { copy(commentState = UiLoadState.Loading) }
            // 댓글 작성 요청
        }
    }
}
