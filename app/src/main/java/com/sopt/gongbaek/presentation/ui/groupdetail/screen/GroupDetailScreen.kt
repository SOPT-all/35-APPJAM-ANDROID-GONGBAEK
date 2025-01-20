package com.sopt.gongbaek.presentation.ui.groupdetail.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.model.GroupComments
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.presentation.type.GroupDetailPagerType
import com.sopt.gongbaek.presentation.type.GroupInfoChipType
import com.sopt.gongbaek.presentation.ui.component.section.GroupInfoSection
import com.sopt.gongbaek.presentation.ui.component.tabpager.CustomTabPager
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.presentation.util.formatGroupTimeDescription
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupDetailRoute(
    navigateGroupRoom: () -> Unit
) {
//    GroupDetailScreen(
//        navigateGroupRoom = navigateGroupRoom
//    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupDetailScreen(
    groupInfo: GroupInfo,
    groupComments: GroupComments,
    navigateGroupRoom: () -> Unit
) {
    val groupDetailTabs: List<String> = GroupDetailPagerType.entries.map { it.description }
    val pagerState = rememberPagerState { groupDetailTabs.size }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StartTitleTopBar(
            modifier = Modifier.background(color = GongBaekTheme.colors.white),
            startTitleResId = R.string.group_detail_top_bar_title,
            onClick = {}
        )
        GroupInfoSection(
            groupStatus = GroupInfoChipType.getChipTypeFromStatus(groupInfo.status),
            groupCategory = GroupInfoChipType.getChipTypeFromCategory(groupInfo.category),
            groupCycle = GroupInfoChipType.getChipTypeFromCycle(groupInfo.cycle),
            groupTitle = groupInfo.title,
            groupTime = formatGroupTimeDescription(groupInfo),
            groupPlace = groupInfo.place,
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
                    0 -> GroupDetailInfoScreen(
                        groupIntroduction = groupInfo.introduction ?: "",
                        groupMaxPeopleCount = 4,
                        groupCurrentPeopleCount = 2
                    )

                    1 -> GroupDetailCommentScreen(
                        groupComments = groupComments,
                        value = "",
                        onValueChanged = {},
                        onRefreshClicked = {},
                        onDeleteClicked = {},
                        onSendClicked = {}
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroupDetailScreenPreview() {
    GONGBAEKTheme {
        GroupDetailScreen(
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
                introduction = "복학하고 친구가 없어서 화요일마다 간단하게 점심 먹을 친구들 구해봐요~ 친구 없는 저를 살려주세요 OTL",
                maxPeopleCount = 6,
                currentPeopleCount = 5,
                isHost = true,
                isApply = true
            ),
            groupComments = GroupComments(
                groupId = 1,
                groupStatus = "RECRUITED",
                groupCycle = "ONCE",
                commentCount = 6,
                groupCommentList = listOf(
                    GroupComments.GroupComment(
                        commentId = 1,
                        commentWriter = "파이리",
                        commentContent = "어디서 만나는거임?",
                        createdAt = "2023-12-27-02-18",
                        isGroupHost = false,
                        isWriter = false
                    ),
                    GroupComments.GroupComment(
                        commentId = 1,
                        commentWriter = "로이임탄",
                        commentContent = "음 아직 안정하긴 했는데 아마 학교 주변 1km 이내일 것 같아요!",
                        createdAt = "2023-12-27-02-19",
                        isGroupHost = true,
                        isWriter = true
                    ),
                    GroupComments.GroupComment(
                        commentId = 1,
                        commentWriter = "훈발놈",
                        commentContent = "저도 아싸라서 친구가 없어요...",
                        createdAt = "2023-12-27-02-24",
                        isGroupHost = false,
                        isWriter = false
                    ),
                    GroupComments.GroupComment(
                        commentId = 1,
                        commentWriter = "훈발놈",
                        commentContent = "저도 아싸라서 친구가 없어요...",
                        createdAt = "2023-12-27-02-24",
                        isGroupHost = false,
                        isWriter = false
                    )
                )
            ),
            navigateGroupRoom = {}
        )
    }
}
