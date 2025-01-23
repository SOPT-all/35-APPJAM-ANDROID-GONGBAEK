package com.sopt.gongbaek.presentation.ui.grouproom.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.model.GroupComments
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.model.GroupMembers
import com.sopt.gongbaek.domain.model.GroupRoom
import com.sopt.gongbaek.presentation.model.ProfileImageList
import com.sopt.gongbaek.presentation.type.GroupInfoChipType
import com.sopt.gongbaek.presentation.ui.component.chip.GroupInfoChip
import com.sopt.gongbaek.presentation.ui.component.section.CommentSection
import com.sopt.gongbaek.presentation.ui.component.section.GroupPlaceDescription
import com.sopt.gongbaek.presentation.ui.component.section.GroupTimeDescription
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.extension.roundedBackgroundWithBorder
import com.sopt.gongbaek.presentation.util.formatGroupTimeDescription
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupRoomRoute(
    viewModel: GroupRoomViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val groupRoomUiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.getGroupRoomInfo()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is GroupRoomContract.SideEffect.NavigateBack -> navigateBack()
                }
            }
    }

    GroupRoomScreen(
        uiState = groupRoomUiState,
        updateInputComment = { inputComment -> viewModel.setEvent(GroupRoomContract.Event.UpdateInputComment(inputComment)) },
        onBackClick = { viewModel.sendSideEffect(GroupRoomContract.SideEffect.NavigateBack) },
        onCommentRefreshClick = { viewModel.setEvent(GroupRoomContract.Event.OnCommentRefreshClick) },
        onCommentPostClick = { viewModel.setEvent(GroupRoomContract.Event.OnCommentPostClick) }
    )
}

@Composable
fun GroupRoomScreen(
    uiState: GroupRoomContract.State,
    updateInputComment: (String) -> Unit,
    onBackClick: () -> Unit,
    onCommentRefreshClick: () -> Unit,
    onCommentPostClick: () -> Unit
) {
    var columnHeight by remember { mutableIntStateOf(0) }
    val systemUiController = rememberSystemUiController()

    DisposableEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )
        onDispose {
//            systemUiController.setStatusBarColor(
//                color = Color.White,
//                darkIcons = true
//            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.img_meetingroom),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(with(LocalDensity.current) { columnHeight.toDp() }),
                contentScale = ContentScale.FillWidth
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { layoutCoordinates ->
                        columnHeight = layoutCoordinates.size.height
                    }
                    .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
            ) {
                StartTitleTopBar(onClick = onBackClick)
                GroupRoomInfoSection(
                    groupStatus = GroupInfoChipType.getChipTypeFromStatus(uiState.groupRoom.groupInfo.status),
                    groupCategory = GroupInfoChipType.getChipTypeFromCategory(uiState.groupRoom.groupInfo.category),
                    groupCycle = GroupInfoChipType.getChipTypeFromCycle(uiState.groupRoom.groupInfo.cycle),
                    groupTitle = uiState.groupRoom.groupInfo.title,
                    groupTime = formatGroupTimeDescription(uiState.groupRoom.groupInfo),
                    groupPlace = uiState.groupRoom.groupInfo.place,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
        GroupRoomPeopleSection(
            groupMembers = uiState.groupRoom.groupMembers
        )
        HorizontalDivider(
            thickness = 8.dp,
            color = GongBaekTheme.colors.gray02
        )
        CommentSection(
            groupComments = uiState.groupRoom.groupComments,
            value = uiState.inputComment,
            onValueChanged = updateInputComment,
            onRefreshClicked = onCommentRefreshClick,
            onSendClicked = onCommentPostClick
        )
    }
}

@Composable
private fun GroupRoomInfoSection(
    groupStatus: GroupInfoChipType,
    groupCategory: GroupInfoChipType,
    groupCycle: GroupInfoChipType,
    groupTitle: String,
    groupTime: String,
    groupPlace: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            GroupInfoChip(groupStatus)
            GroupInfoChip(groupCategory)
            GroupInfoChip(groupCycle)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = groupTitle,
            color = GongBaekTheme.colors.white,
            style = GongBaekTheme.typography.title1.b20
        )
        Spacer(modifier = Modifier.height(12.dp))
        GroupTimeDescription(
            description = groupTime,
            textColor = GongBaekTheme.colors.white,
            textStyle = GongBaekTheme.typography.caption2.r12
        )
        Spacer(modifier = Modifier.height(4.dp))
        GroupPlaceDescription(
            description = groupPlace,
            textColor = GongBaekTheme.colors.white,
            textStyle = GongBaekTheme.typography.caption2.r12
        )
    }
}

@Composable
private fun GroupRoomPeopleSection(
    groupMembers: GroupMembers
) {
    val imageList = ProfileImageList.profileImageList

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
            .padding(vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_people_18),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(
                    R.string.grouproom_people_count,
                    groupMembers.currentPeopleCount,
                    groupMembers.maxPeopleCount
                ),
                color = GongBaekTheme.colors.gray10,
                style = GongBaekTheme.typography.title2.sb18
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(end = 16.dp)
        ) {
            items(groupMembers.members) { member ->
                val profileImageResId = if (imageList.isNotEmpty() && member.profileImg in 1..imageList.size) {
                    imageList[member.profileImg - 1]
                } else {
                    R.drawable.img_detail_profile_1
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box {
                        Image(
                            painter = painterResource(profileImageResId),
                            contentDescription = null,
                            modifier = Modifier
                                .roundedBackgroundWithBorder(
                                    cornerRadius = 2.dp,
                                    backgroundColor = GongBaekTheme.colors.white,
                                    borderColor = GongBaekTheme.colors.gray02,
                                    borderWidth = 1.dp
                                )
                                .clip(RoundedCornerShape(2.dp))
                                .size(80.dp)
                        )
                        if (member.isHost) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(start = 6.dp, bottom = 6.dp)
                                    .roundedBackgroundWithBorder(
                                        cornerRadius = 4.dp,
                                        backgroundColor = GongBaekTheme.colors.gray09
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.grouproom_people_chip_host),
                                    modifier = Modifier
                                        .padding(horizontal = 6.dp, vertical = 2.dp),
                                    color = GongBaekTheme.colors.mainOrange,
                                    style = GongBaekTheme.typography.caption2.m12
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = member.nickname,
                        color = GongBaekTheme.colors.gray10,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = GongBaekTheme.typography.caption1.m13
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GroupRoomScreenPreview1() {
    GONGBAEKTheme {
        GroupRoomScreen(
            uiState = GroupRoomContract.State(
                groupRoomLoadState = UiLoadState.Idle,
                groupRoom = GroupRoom(
                    groupInfo = GroupInfo(
                        status = "CLOSED",
                        category = "PLAYING",
                        cycle = "WEEKLY",
                        title = "화석의 튜스데이 점심 클럽",
                        dayOfWeek = "THU",
                        startTime = 13.5,
                        endTime = 15.5,
                        place = "학교 피아노 앞"
                    ),
                    groupMembers = GroupMembers(
                        maxPeopleCount = 6,
                        currentPeopleCount = 5,
                        members = listOf(
                            GroupMembers.Member(
                                nickname = "로이임탄",
                                isHost = true
                            ),
                            GroupMembers.Member(
                                nickname = "아싸 대학생",
                                isHost = false
                            ),
                            GroupMembers.Member(
                                nickname = "파이리",
                                isHost = false
                            ),
                            GroupMembers.Member(
                                nickname = "노는게 제일좋아",
                                isHost = false
                            ),
                            GroupMembers.Member(
                                nickname = "소니",
                                isHost = false
                            )
                        )
                    ),
                    groupComments = GroupComments(
                        groupStatus = "CLOSED",
                        groupCycle = "WEEKLY",
                        commentCount = 0,
                        groupCommentList = listOf(
                            GroupComments.GroupComment(
                                commentWriter = "로이임탄",
                                commentContent = "왜 화장실에서 밥을 먹어요?",
                                createdAt = "2025-12-27-02-18"
                            )
                        )
                    )
                )
            ),
            updateInputComment = {},
            onBackClick = {},
            onCommentRefreshClick = {},
            onCommentPostClick = {}
        )
    }
}
