package com.sopt.gongbaek.presentation.ui.grouproom.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.model.GroupComment
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.model.GroupPeople
import com.sopt.gongbaek.presentation.type.GroupInfoChipType
import com.sopt.gongbaek.presentation.ui.component.chip.GroupInfoChip
import com.sopt.gongbaek.presentation.ui.component.section.CommentSection
import com.sopt.gongbaek.presentation.ui.component.section.GroupPlaceDescription
import com.sopt.gongbaek.presentation.ui.component.section.GroupTimeDescription
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.presentation.util.extension.createGroupTimeDescription
import com.sopt.gongbaek.presentation.util.extension.roundedBackgroundWithBorder
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupRoomRoute() {
//    GroupRoomScreen()
}

@Composable
fun GroupRoomScreen(
    groupInfo: GroupInfo,
    groupPeople: GroupPeople,
    groupComment: GroupComment,
    inputComment: String,
    onInputCommentChange: (String) -> Unit,
    onBackClicked: () -> Unit,
    onRefreshClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onSendClicked: () -> Unit
) {
    var columnHeight by remember { mutableIntStateOf(0) }

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
            ) {
                StartTitleTopBar(onClick = onBackClicked)
                GroupRoomInfoSection(
                    groupStatus = GroupInfoChipType.getChipTypeFromStatus(groupInfo.status),
                    groupCategory = GroupInfoChipType.getChipTypeFromCategory(groupInfo.category),
                    groupCycle = GroupInfoChipType.getChipTypeFromCycle(groupInfo.cycle),
                    groupTitle = groupInfo.title,
                    groupTime = createGroupTimeDescription(groupInfo),
                    groupPlace = groupInfo.place,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        GroupRoomPeopleSection(
            groupPeople = groupPeople
        )
        HorizontalDivider(
            thickness = 8.dp,
            color = GongBaekTheme.colors.gray02
        )
        CommentSection(
            groupComment = groupComment,
            value = inputComment,
            onValueChanged = onInputCommentChange,
            onRefreshClicked = onRefreshClicked,
            onDeleteClicked = onDeleteClicked,
            onSendClicked = onSendClicked
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
        Spacer(modifier = Modifier.height(2.dp))
        GroupPlaceDescription(
            description = groupPlace,
            textColor = GongBaekTheme.colors.white,
            textStyle = GongBaekTheme.typography.caption2.r12
        )
    }
}

@Composable
private fun GroupRoomPeopleSection(
    groupPeople: GroupPeople
) {
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
                    groupPeople.currentPeopleCount,
                    groupPeople.maxPeopleCount
                ),
                color = GongBaekTheme.colors.gray10,
                style = GongBaekTheme.typography.title2.sb18
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(end = 16.dp) // 마지막 아이템 오른쪽 패딩 추가
        ) {
            items(groupPeople.members) { member ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_background),
                            contentDescription = null
                        )
                        if (member.isHost) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(start = 6.dp, bottom = 6.dp)
                                    .roundedBackgroundWithBorder(
                                        cornerRadius = 4.dp,
                                        backgroundColor = GongBaekTheme.colors.subOrange
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.grouproom_people_chip_host),
                                    modifier = Modifier
                                        .padding(horizontal = 6.dp, vertical = 1.dp),
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
            groupInfo = GroupInfo(
                groupId = 1,
                coverImg = 1,
                status = "CLOSED",
                category = "PLAYING",
                cycle = "ONCE",
                title = "화석의 튜스데이 점심 클럽",
                date = "2025-04-06",
                dayOfWeek = "THU",
                startTime = 13.5,
                endTime = 15.5,
                place = "학교 피아노 앞",
                introduction = "",
                maxPeopleCount = 6,
                currentPeopleCount = 5,
                isHost = true,
                isApply = true
            ),
            groupPeople = GroupPeople(
                maxPeopleCount = 6,
                currentPeopleCount = 5,
                members = listOf(
                    GroupPeople.Member(
                        profileImg = 1,
                        nickname = "로이임탄",
                        isHost = true
                    ),
                    GroupPeople.Member(
                        profileImg = 1,
                        nickname = "아싸 대학생",
                        isHost = false
                    ),
                    GroupPeople.Member(
                        profileImg = 1,
                        nickname = "파이리",
                        isHost = false
                    ),
                    GroupPeople.Member(
                        profileImg = 1,
                        nickname = "노는게 제일좋아",
                        isHost = false
                    ),
                    GroupPeople.Member(
                        profileImg = 1,
                        nickname = "롯데월드",
                        isHost = false
                    )
                )
            ),
            groupComment = GroupComment(
                commentCount = 6,
                commentList = listOf(
                    GroupComment.Comment(
                        groupId = 1,
                        groupType = "ONCE",
                        commentId = 1,
                        commentWriter = "파이리",
                        commentContent = "어디서 만나는거임?",
                        createdAt = "2023-12-27-02-18",
                        isGroupHost = false,
                        isWriter = false
                    ),
                    GroupComment.Comment(
                        groupId = 1,
                        groupType = "ONCE",
                        commentId = 1,
                        commentWriter = "로이임탄",
                        commentContent = "음 아직 안정하긴 했는데 아마 학교 주변 1km 이내일 것 같아요!",
                        createdAt = "2023-12-27-02-19",
                        isGroupHost = true,
                        isWriter = true
                    ),
                    GroupComment.Comment(
                        groupId = 1,
                        groupType = "ONCE",
                        commentId = 1,
                        commentWriter = "훈발놈",
                        commentContent = "저도 아싸라서 친구가 없어요...",
                        createdAt = "2023-12-27-02-24",
                        isGroupHost = false,
                        isWriter = false
                    )
                )
            ),
            inputComment = "",
            onInputCommentChange = {},
            onBackClicked = {},
            onRefreshClicked = {},
            onDeleteClicked = {},
            onSendClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroupRoomScreenPreview2() {
    GONGBAEKTheme {
        GroupRoomScreen(
            groupInfo = GroupInfo(
                groupId = 1,
                coverImg = 1,
                status = "RECRUITING",
                category = "PLAYING",
                cycle = "WEEKLY",
                title = "화석의 튜스데이 점심 클럽",
                date = null,
                dayOfWeek = "THU",
                startTime = 13.5,
                endTime = 15.5,
                place = "학교 피아노 앞",
                introduction = "",
                maxPeopleCount = 6,
                currentPeopleCount = 5,
                isHost = true,
                isApply = true
            ),
            groupPeople = GroupPeople(
                maxPeopleCount = 6,
                currentPeopleCount = 5,
                members = listOf(
                    GroupPeople.Member(
                        profileImg = 1,
                        nickname = "로이임탄",
                        isHost = true
                    ),
                    GroupPeople.Member(
                        profileImg = 1,
                        nickname = "아싸 대학생",
                        isHost = false
                    ),
                    GroupPeople.Member(
                        profileImg = 1,
                        nickname = "파이리",
                        isHost = false
                    ),
                    GroupPeople.Member(
                        profileImg = 1,
                        nickname = "노는게 제일좋아",
                        isHost = false
                    ),
                    GroupPeople.Member(
                        profileImg = 1,
                        nickname = "롯데월드",
                        isHost = false
                    )
                )
            ),
            groupComment = GroupComment(
                commentCount = 6,
                commentList = listOf(
                    GroupComment.Comment(
                        groupId = 1,
                        groupType = "ONCE",
                        commentId = 1,
                        commentWriter = "파이리",
                        commentContent = "어디서 만나는거임?",
                        createdAt = "2023-12-27-02-18",
                        isGroupHost = false,
                        isWriter = false
                    ),
                    GroupComment.Comment(
                        groupId = 1,
                        groupType = "ONCE",
                        commentId = 1,
                        commentWriter = "로이임탄",
                        commentContent = "음 아직 안정하긴 했는데 아마 학교 주변 1km 이내일 것 같아요!",
                        createdAt = "2023-12-27-02-19",
                        isGroupHost = true,
                        isWriter = true
                    ),
                    GroupComment.Comment(
                        groupId = 1,
                        groupType = "ONCE",
                        commentId = 1,
                        commentWriter = "훈발놈",
                        commentContent = "저도 아싸라서 친구가 없어요...",
                        createdAt = "2023-12-27-02-24",
                        isGroupHost = false,
                        isWriter = false
                    )
                )
            ),
            inputComment = "",
            onInputCommentChange = {},
            onBackClicked = {},
            onRefreshClicked = {},
            onDeleteClicked = {},
            onSendClicked = {}
        )
    }
}
