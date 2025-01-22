package com.sopt.gongbaek.presentation.ui.groupdetail.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.presentation.type.GongBaekDialogType
import com.sopt.gongbaek.presentation.type.GroupDetailPagerType
import com.sopt.gongbaek.presentation.type.GroupInfoChipType
import com.sopt.gongbaek.presentation.ui.component.dialog.GongBaekDialog
import com.sopt.gongbaek.presentation.ui.component.section.CommentSection
import com.sopt.gongbaek.presentation.ui.component.section.GroupInfoSection
import com.sopt.gongbaek.presentation.ui.component.tabpager.CustomTabPager
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.formatGroupTimeDescription
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupDetailRoute(
    viewModel: GroupDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateGroupRoom: () -> Unit
) {
    val groupDetailUiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is GroupDetailContract.SideEffect.NavigateBack -> navigateBack()
                    is GroupDetailContract.SideEffect.NavigateGroupRoom -> navigateGroupRoom()
                }
            }
    }

    GroupDetailScreen(
        uiState = groupDetailUiState,
        onBackClick = { viewModel.sendSideEffect(GroupDetailContract.SideEffect.NavigateBack) },
        onGroupInfoTabClick = { viewModel.setEvent(GroupDetailContract.Event.OnGroupInfoTabClick) },
        onApplyClick = { viewModel.setEvent(GroupDetailContract.Event.OnApplyClick) },
        onDialogConfirmClick = { viewModel.setEvent(GroupDetailContract.Event.OnDialogConfirmClick) },
        onDialogDismissClick = { viewModel.setEvent(GroupDetailContract.Event.OnDialogDismissClick) },
        onCommentTabClick = { viewModel.setEvent(GroupDetailContract.Event.OnCommentTabClick) },
        updateInputComment = { inputComment -> viewModel.setEvent(GroupDetailContract.Event.UpdateInputComment(inputComment)) },
        onCommentRefreshClick = { viewModel.setEvent(GroupDetailContract.Event.OnCommentRefreshClick) },
        onCommentPostClick = { viewModel.setEvent(GroupDetailContract.Event.OnCommentPostClick) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupDetailScreen(
    uiState: GroupDetailContract.State,
    onBackClick: () -> Unit,
    onGroupInfoTabClick: () -> Unit,
    onApplyClick: () -> Unit,
    onDialogConfirmClick: () -> Unit,
    onDialogDismissClick: () -> Unit,
    onCommentTabClick: () -> Unit,
    updateInputComment: (String) -> Unit,
    onCommentRefreshClick: () -> Unit,
    onCommentPostClick: () -> Unit
) {
    val groupDetailTabs: List<String> = GroupDetailPagerType.entries.map { it.description }
    val pagerState = rememberPagerState { groupDetailTabs.size }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StartTitleTopBar(
            modifier = Modifier.background(color = GongBaekTheme.colors.white),
            startTitleResId = R.string.group_detail_top_bar_title,
            onClick = onBackClick
        )
        GroupInfoSection(
            groupStatus = GroupInfoChipType.getChipTypeFromStatus(uiState.groupInfo.status),
            groupCategory = GroupInfoChipType.getChipTypeFromCategory(uiState.groupInfo.category),
            groupCycle = GroupInfoChipType.getChipTypeFromCycle(uiState.groupInfo.cycle),
            groupCover = uiState.groupInfo.coverImg,
            groupTitle = uiState.groupInfo.title,
            groupTime = formatGroupTimeDescription(uiState.groupInfo),
            groupPlace = uiState.groupInfo.place,
            modifier = Modifier
                .background(color = GongBaekTheme.colors.white)
                .padding(16.dp)
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 8.dp,
            color = GongBaekTheme.colors.gray02
        )
        CustomTabPager(
            tabs = groupDetailTabs,
            pagerState = pagerState
        )
        HorizontalPager(
            state = pagerState,
            pageContent = { page ->
                when (page) {
                    0 -> GroupDetailInfoSection(
                        groupInfo = uiState.groupInfo,
                        groupMaxPeopleCount = 4,
                        groupCurrentPeopleCount = 2,
                        onApplyClick = onApplyClick
                    )

                    1 -> CommentSection(
                        groupComments = uiState.groupComments,
                        value = uiState.inputComment,
                        onValueChanged = updateInputComment,
                        onRefreshClicked = onCommentRefreshClick,
                        onSendClicked = onCommentPostClick
                    )
                }
            }
        )
    }

    if (uiState.groupApplyState == UiLoadState.Success) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        ) {
            Dialog(
                onDismissRequest = onDialogDismissClick
            ) {
                GongBaekDialog(
                    gongBaekDialogType = GongBaekDialogType.ENTER_SUCCESS,
                    onConfirmButtonClick = onDialogConfirmClick,
                    onDismissButtonClick = onDialogDismissClick
                )
            }
        }
    }

    if (uiState.groupApplyState == UiLoadState.Error) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        ) {
            Dialog(
                onDismissRequest = onDialogDismissClick
            ) {
                GongBaekDialog(
                    gongBaekDialogType = GongBaekDialogType.ENTER_FAIL,
                    onConfirmButtonClick = onDialogConfirmClick,
                    onDismissButtonClick = onDialogDismissClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GroupDetailScreenPreview() {
    GONGBAEKTheme {
        GroupDetailScreen(
            uiState = GroupDetailContract.State(
                groupInfo = GroupInfo(
                    status = "RECRUITING",
                    isHost = false,
                    isApply = false
                ),
                groupApplyState = UiLoadState.Error
            ),
            onBackClick = {},
            onGroupInfoTabClick = {},
            onApplyClick = {},
            onDialogConfirmClick = {},
            onDialogDismissClick = {},
            onCommentTabClick = {},
            updateInputComment = {},
            onCommentRefreshClick = {},
            onCommentPostClick = {}
        )
    }
}
