package com.sopt.gongbaek.presentation.ui.mygroup.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
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
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.type.MyGroupPagerType
import com.sopt.gongbaek.presentation.ui.component.tabpager.CustomTabPager
import com.sopt.gongbaek.presentation.ui.component.topbar.CenterTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyGroupRoute(
    viewModel: MyGroupViewModel = hiltViewModel(),
    navigateGroupDetail: (Int, String) -> Unit,
    navigateGroupRoom: (Int, String) -> Unit
) {
    val myGroupUiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val myGroupTabs: List<String> = MyGroupPagerType.entries.map { it.description }
    val pagerState = rememberPagerState { myGroupTabs.size }

    LaunchedEffect(pagerState.currentPage) {
        when (pagerState.currentPage) {
            0 -> viewModel.setEvent(MyGroupContract.Event.OnRegisterGroupsTabClick)
            1 -> viewModel.setEvent(MyGroupContract.Event.OnApplyGroupsTabClick)
        }
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is MyGroupContract.SideEffect.NavigateGroupDetail -> {
                        navigateGroupDetail(sideEffect.groupId, sideEffect.groupCycle)
                    }

                    is MyGroupContract.SideEffect.NavigateGroupRoom -> {
                        navigateGroupRoom(sideEffect.groupId, sideEffect.groupCycle)
                    }
                }
            }
    }

    MyGroupScreen(
        uiState = myGroupUiState,
        myGroupTabs = myGroupTabs,
        pagerState = pagerState,
        onGroupDetailButtonClick = { groupId, groupCycle -> viewModel.sendSideEffect(MyGroupContract.SideEffect.NavigateGroupDetail(groupId, groupCycle)) },
        onGroupRoomButtonClick = { groupId, groupCycle -> viewModel.sendSideEffect(MyGroupContract.SideEffect.NavigateGroupRoom(groupId, groupCycle)) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyGroupScreen(
    uiState: MyGroupContract.State,
    myGroupTabs: List<String>,
    pagerState: PagerState,
    onGroupDetailButtonClick: (Int, String) -> Unit,
    onGroupRoomButtonClick: (Int, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .padding(WindowInsets.navigationBars.asPaddingValues())
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        CenterTitleTopBar(
            centerTitleResId = R.string.my_group_top_bar_title,
            modifier = Modifier.background(color = GongBaekTheme.colors.white)
        )
        CustomTabPager(
            tabs = myGroupTabs,
            pagerState = pagerState
        )
        HorizontalPager(
            state = pagerState,
            pageContent = { page ->
                when (page) {
                    0 -> {
                        MyGroupScreenContent(
                            activeGroups = uiState.registerActiveGroups,
                            closedGroups = uiState.registerClosedGroups,
                            onGroupDetailButtonClick = onGroupDetailButtonClick,
                            onGroupRoomButtonClick = onGroupRoomButtonClick
                        )
                    }

                    1 -> {
                        MyGroupScreenContent(
                            activeGroups = uiState.applyActiveGroups,
                            closedGroups = uiState.applyClosedGroups,
                            onGroupDetailButtonClick = onGroupDetailButtonClick,
                            onGroupRoomButtonClick = onGroupRoomButtonClick
                        )
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
private fun MyGroupScreenPreview() {
    GONGBAEKTheme {
        MyGroupScreen(
            uiState = MyGroupContract.State(),
            myGroupTabs = MyGroupPagerType.entries.map { it.description },
            pagerState = rememberPagerState { MyGroupPagerType.entries.map { it.description }.size },
            onGroupDetailButtonClick = { _, _ -> },
            onGroupRoomButtonClick = { _, _ -> }
        )
    }
}
