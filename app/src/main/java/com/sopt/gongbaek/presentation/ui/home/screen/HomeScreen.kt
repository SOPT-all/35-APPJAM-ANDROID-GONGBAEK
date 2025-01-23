package com.sopt.gongbaek.presentation.ui.home.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.domain.model.NearestGroup
import com.sopt.gongbaek.domain.model.RecommendGroupInfo
import com.sopt.gongbaek.presentation.ui.home.component.section.HomeBannerSection
import com.sopt.gongbaek.presentation.ui.home.component.section.MemberRecommendSection
import com.sopt.gongbaek.presentation.ui.home.component.section.NearestGroupSection
import com.sopt.gongbaek.presentation.ui.home.component.section.OnceRecommendSection
import com.sopt.gongbaek.presentation.ui.home.component.section.WeekRecommendSection
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun HomeRoute(
    navigateGroupDetail: (Int, String) -> Unit,
    navigateGroupList: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                if (sideEffect is HomeContract.SideEffect.NavigateToGroupDetail) {
                    navigateGroupDetail(sideEffect.groupId, sideEffect.groupType)
                }
                if (sideEffect is HomeContract.SideEffect.NavigateToGroupList) {
                    navigateGroupList()
                }
            }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(HomeContract.Event.OnFetchHomeInfo)
        viewModel.setEvent(HomeContract.Event.OnFetchLatestOnceGroup)
        viewModel.setEvent(HomeContract.Event.OnFetchLatestWeekGroup)
        viewModel.setEvent(HomeContract.Event.OnFetchUserProfile)
        viewModel.setEvent(HomeContract.Event.OnFetchUserLectureTimetable)
    }

    HomeScreen(
        university = uiState.userProfile.schoolName,
        userNickname = uiState.userProfile.nickname,
        nearestGroup = uiState.nearestGroup,
        onceRecommendGroupInfo = uiState.onceRecommendGroupList,
        weekRecommendGroupInfo = uiState.weekRecommendGroupList,
        onClickWeekRecommendItem = { groupId, groupCycle ->
            viewModel.sendSideEffect(HomeContract.SideEffect.NavigateToGroupDetail(groupId, groupCycle))
        },
        onClickOnceRecommendItem = { groupId, groupCycle ->
            viewModel.sendSideEffect(HomeContract.SideEffect.NavigateToGroupDetail(groupId, groupCycle))
        },
        onNearestGroupClick = { groupId, groupCycle ->
            viewModel.sendSideEffect(HomeContract.SideEffect.NavigateToGroupDetail(groupId, groupCycle))
        },
        onFillGroupClick = {
            viewModel.sendSideEffect(HomeContract.SideEffect.NavigateToGroupList)
        }
    )
}

@Composable
private fun HomeScreen(
    university: String,
    userNickname: String,
    nearestGroup: NearestGroup,
    weekRecommendGroupInfo: List<RecommendGroupInfo>,
    onceRecommendGroupInfo: List<RecommendGroupInfo>,
    onClickWeekRecommendItem: (Int, String) -> Unit,
    onClickOnceRecommendItem: (Int, String) -> Unit,
    onNearestGroupClick: (Int, String) -> Unit,
    onFillGroupClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            NearestGroupSection(
                university = university,
                nearestGroup = nearestGroup,
                onNearestGroupClick = onNearestGroupClick,
                onFillGroupClick = onFillGroupClick,
                modifier = Modifier.padding(bottom = 30.dp)
            )
        }

        item {
            WeekRecommendSection(
                userNickname = userNickname,
                weekRecommendGroupInfo = weekRecommendGroupInfo,
                onClickWeekRecommendItem = onClickWeekRecommendItem,
                modifier = Modifier.padding(bottom = 30.dp)
            )
        }

        item {
            OnceRecommendSection(
                onceRecommendGroupInfo = onceRecommendGroupInfo,
                onClickOnceRecommendItem = onClickOnceRecommendItem,
                modifier = Modifier.padding(bottom = 30.dp)
            )
        }

        item {
            HomeBannerSection(
                modifier = Modifier.padding(bottom = 30.dp)
            )
        }

        item {
            MemberRecommendSection()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    GONGBAEKTheme {
        HomeScreen(
            university = "건국대학교 서울캠퍼스",
            nearestGroup = NearestGroup(
                weekDate = "2021-09-20",
                startTime = 18.0,
                endTime = 20.0
            ),
            userNickname = "김대현",
            weekRecommendGroupInfo = listOf(
                RecommendGroupInfo(
                    groupTitle = "스터디 모임",
                    nickname = "김대현",
                    weekDate = "2021-09-20",
                    profileImg = 5
                ),
                RecommendGroupInfo(
                    groupTitle = "운동 모임",
                    nickname = "김대현1",
                    weekDate = "2021-09-20",
                    profileImg = 1
                ),
                RecommendGroupInfo(
                    groupTitle = "스터디 모임",
                    nickname = "김대현2",
                    weekDate = "2021-09-20",
                    profileImg = 3
                ),
                RecommendGroupInfo(
                    groupTitle = "운동 모임",
                    nickname = "김대현3",
                    weekDate = "2021-09-20"
                )
            ),
            onClickWeekRecommendItem = { _, _ -> },
            onClickOnceRecommendItem = { _, _ -> },
            onNearestGroupClick = { _, _ -> },
            onFillGroupClick = {},
            onceRecommendGroupInfo = listOf(
                RecommendGroupInfo(
                    groupTitle = "스터디 모임",
                    nickname = "김대현",
                    weekDate = "2021-09-20",
                    profileImg = 5
                ),
                RecommendGroupInfo(
                    groupTitle = "운동 모임",
                    nickname = "김대현1",
                    weekDate = "2021-09-20",
                    profileImg = 1
                ),
                RecommendGroupInfo(
                    groupTitle = "스터디 모임",
                    nickname = "김대현2",
                    weekDate = "2021-09-20",
                    profileImg = 3
                ),
                RecommendGroupInfo(
                    groupTitle = "운동 모임",
                    nickname = "김대현3",
                    weekDate = "2021-09-20"
                )
            )
        )
    }
}
