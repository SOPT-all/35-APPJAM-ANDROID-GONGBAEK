package com.sopt.gongbaek.presentation.ui.grouproom.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.domain.model.Comment
import com.sopt.gongbaek.domain.model.GroupRoom
import com.sopt.gongbaek.domain.usecase.GetGroupCommentsUseCase
import com.sopt.gongbaek.domain.usecase.LoadGroupRoomScreenUseCase
import com.sopt.gongbaek.domain.usecase.PostCommentUseCase
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupRoomViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadGroupRoomScreenUseCase: LoadGroupRoomScreenUseCase,
    private val getGroupCommentsUseCase: GetGroupCommentsUseCase,
    private val postCommentUseCase: PostCommentUseCase
) : BaseViewModel<GroupRoomContract.State, GroupRoomContract.Event, GroupRoomContract.SideEffect>() {

    init {
        val groupId: Int = savedStateHandle["groupId"] ?: 0
        val groupCycle: String = savedStateHandle["groupCycle"] ?: ""
        updateGroupRoom {
            copy(
                groupInfo = groupInfo.copy(
                    groupId = groupId,
                    cycle = groupCycle
                )
            )
        }
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

    private fun updateGroupRoom(update: GroupRoom.() -> GroupRoom) =
        setState { copy(groupRoom = groupRoom.update()) }

    fun getGroupRoomInfo() {
        viewModelScope.launch {
            setState { copy(groupRoomLoadState = UiLoadState.Loading) }
            loadGroupRoomScreenUseCase(
                groupId = currentState.groupRoom.groupInfo.groupId,
                groupType = currentState.groupRoom.groupInfo.cycle
            ).fold(
                onSuccess = { groupRoom ->
                    setState { copy(groupRoomLoadState = UiLoadState.Success, groupRoom = groupRoom) }
                },
                onFailure = {
                    setState { copy(groupRoomLoadState = UiLoadState.Error) }
                }
            )
        }
    }

    private fun getGroupComment() {
        viewModelScope.launch {
            setState { copy(commentState = UiLoadState.Loading) }
            getGroupCommentsUseCase(
                isPublic = false,
                groupId = currentState.groupRoom.groupInfo.groupId,
                groupType = currentState.groupRoom.groupInfo.cycle
            ).fold(
                onSuccess = { groupComments ->
                    setState { copy(commentState = UiLoadState.Success) }
                    updateGroupRoom { copy(groupComments = groupComments) }
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
                    groupId = currentState.groupRoom.groupInfo.groupId,
                    cycle = currentState.groupRoom.groupInfo.cycle,
                    isPublic = false,
                    content = currentState.inputComment
                )
            ).fold(
                onSuccess = { groupComments ->
                    setState { copy(commentState = UiLoadState.Success, inputComment = "") }
                    updateGroupRoom { copy(groupComments = groupComments) }
                },
                onFailure = {
                    setState { copy(commentState = UiLoadState.Error) }
                }
            )
        }
    }
}
