package com.sopt.gongbaek.presentation.ui.groupdetail.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<GroupDetailContract.State, GroupDetailContract.Event, GroupDetailContract.SideEffect>() {

    init {
        val groupId: Int = savedStateHandle["groupId"] ?: 0
        val groupCycle: String = savedStateHandle["groupCycle"] ?: ""
        getGroupDetailInfo(groupId, groupCycle)
    }

    override fun createInitialState(): GroupDetailContract.State = GroupDetailContract.State()

    override suspend fun handleEvent(event: GroupDetailContract.Event) {
        when (event) {
            is GroupDetailContract.Event.OnGroupInfoTabClick -> {
                getGroupDetailInfo(currentState.groupInfo.groupId, currentState.groupInfo.cycle)
            }
            is GroupDetailContract.Event.OnApplyClick -> {
                applyGroup()
            }
            is GroupDetailContract.Event.OnDialogConfirmClick -> {
                setSideEffect(GroupDetailContract.SideEffect.NavigateGroupRoom)
            }
            is GroupDetailContract.Event.OnDialogDismissClick -> setState {
                copy(groupApplyState = UiLoadState.Idle)
            }
            is GroupDetailContract.Event.OnCommentTabClick -> {
                getGroupDetailInfo(currentState.groupInfo.groupId, currentState.groupInfo.cycle)
            }
            is GroupDetailContract.Event.UpdateInputComment -> setState {
                copy(inputComment = event.inputComment)
            }
            is GroupDetailContract.Event.OnCommentRefreshClick -> {
                getGroupComment()
            }
            is GroupDetailContract.Event.OnCommentPostClick -> {
                postInputComment()
            }
        }
    }

    fun sendSideEffect(sideEffect: GroupDetailContract.SideEffect) = setSideEffect(sideEffect)

    private fun getGroupDetailInfo(groupId: Int, groupCycle: String) {
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
            // 모임 정보, 모임 호소트, 댓글 정보를 조회
        }
    }

    private fun applyGroup() {
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
            // 모임 신청
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
