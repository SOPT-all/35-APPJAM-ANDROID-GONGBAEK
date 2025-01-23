package com.sopt.gongbaek.presentation.ui.groupdetail.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.domain.model.Comment
import com.sopt.gongbaek.domain.model.GroupDetail
import com.sopt.gongbaek.domain.usecase.ApplyGroupUseCase
import com.sopt.gongbaek.domain.usecase.GetGroupCommentsUseCase
import com.sopt.gongbaek.domain.usecase.LoadGroupDetailScreenUseCase
import com.sopt.gongbaek.domain.usecase.PostCommentUseCase
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GroupDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadGroupDetailScreenUseCase: LoadGroupDetailScreenUseCase,
    private val applyGroupUseCase: ApplyGroupUseCase,
    private val getGroupCommentsUseCase: GetGroupCommentsUseCase,
    private val postCommentUseCase: PostCommentUseCase
) : BaseViewModel<GroupDetailContract.State, GroupDetailContract.Event, GroupDetailContract.SideEffect>() {

    init {
        val groupId: Int = savedStateHandle["groupId"] ?: 0
        val groupCycle: String = savedStateHandle["groupCycle"] ?: ""
        updateGroupDetail {
            copy(
                groupInfo = groupInfo.copy(
                    groupId = groupId,
                    cycle = groupCycle
                )
            )
        }
    }

    override fun createInitialState(): GroupDetailContract.State = GroupDetailContract.State()

    override suspend fun handleEvent(event: GroupDetailContract.Event) {
        when (event) {
            is GroupDetailContract.Event.OnGroupInfoTabClick -> {
                getGroupDetailInfo()
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
                getGroupDetailInfo()
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

    private fun updateGroupDetail(update: GroupDetail.() -> GroupDetail) =
        setState { copy(groupDetail = groupDetail.update()) }

    private fun getGroupDetailInfo() {
        viewModelScope.launch {
            setState { copy(groupDetailLoadState = UiLoadState.Loading) }
            loadGroupDetailScreenUseCase(
                groupId = currentState.groupDetail.groupInfo.groupId,
                groupType = currentState.groupDetail.groupInfo.cycle
            ).fold(
                onSuccess = { groupDetail ->
                    setState { copy(groupDetailLoadState = UiLoadState.Success, groupDetail = groupDetail) }
                    Timber.tag("groupDetailLoadState1").d("${currentState.groupDetailLoadState}")
                    Timber.tag("groupDetail").d("${currentState.groupDetail}")
                },
                onFailure = {
                    setState { copy(groupDetailLoadState = UiLoadState.Error) }
                    Timber.tag("groupDetailLoadState2").d("${currentState.groupDetailLoadState}")
                }
            )
        }
    }

    private fun applyGroup() {
        viewModelScope.launch {
            setState { copy(groupApplyState = UiLoadState.Loading) }
            applyGroupUseCase(
                groupId = currentState.groupDetail.groupInfo.groupId,
                groupType = currentState.groupDetail.groupInfo.cycle
            ).fold(
                onSuccess = { setState { copy(groupApplyState = UiLoadState.Success) } },
                onFailure = { setState { copy(groupApplyState = UiLoadState.Error) } }
            )
        }
    }

    private fun getGroupComment() {
        viewModelScope.launch {
            setState { copy(commentState = UiLoadState.Loading) }
            getGroupCommentsUseCase(
                isPublic = true,
                groupId = currentState.groupDetail.groupInfo.groupId,
                groupType = currentState.groupDetail.groupInfo.cycle
            ).fold(
                onSuccess = { groupComments ->
                    setState { copy(commentState = UiLoadState.Success) }
                    updateGroupDetail { copy(groupComments = groupComments) }
                },
                onFailure = {
                    setState { copy(commentState = UiLoadState.Error) }
                }
            )
        }
    }

    private fun postInputComment() {
        viewModelScope.launch {
            setState { copy(commentState = UiLoadState.Loading) }
            postCommentUseCase(
                comment = Comment(
                    groupId = currentState.groupDetail.groupInfo.groupId,
                    cycle = currentState.groupDetail.groupInfo.cycle,
                    isPublic = true,
                    content = currentState.inputComment
                )
            ).fold(
                onSuccess = { groupComments ->
                    setState { copy(commentState = UiLoadState.Success, inputComment = "") }
                    updateGroupDetail { copy(groupComments = groupComments) }
                },
                onFailure = {
                    setState { copy(commentState = UiLoadState.Error) }
                }
            )
        }
    }
}
