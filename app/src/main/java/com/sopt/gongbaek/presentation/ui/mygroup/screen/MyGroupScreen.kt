package com.sopt.gongbaek.presentation.ui.mygroup.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun MyGroupRoute(
    viewModel: MyGroupViewModel = hiltViewModel(),
    navigateGroupDetail: () -> Unit,
    navigateGroupRoom: () -> Unit
) {
    val myGroupUiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is MyGroupContract.SideEffect.NavigateGroupDetail -> navigateGroupDetail()
                    is MyGroupContract.SideEffect.NavigateGroupRoom -> navigateGroupRoom()
                }
            }
    }

    MyGroupScreen(
        uiState = myGroupUiState,
        onRegisterGroupsTab = { viewModel.setEvent(MyGroupContract.Event.GetRegisterGroups) },
        onApplyGroupsTab = { viewModel.setEvent(MyGroupContract.Event.GetApplyGroups) },
        onGroupDetailButtonClick = { viewModel.sendSideEffect(MyGroupContract.SideEffect.NavigateGroupDetail) },
        onGroupRoomButtonClick = { viewModel.sendSideEffect(MyGroupContract.SideEffect.NavigateGroupRoom) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyGroupScreen(
    uiState: MyGroupContract.State,
    onRegisterGroupsTab: () -> Unit,
    onApplyGroupsTab: () -> Unit,
    onGroupDetailButtonClick: () -> Unit,
    onGroupRoomButtonClick: () -> Unit
) {
    val myGroupTabs: List<String> = MyGroupPagerType.entries.map { it.description }
    val pagerState = rememberPagerState { myGroupTabs.size }

    Column(
        modifier = Modifier.fillMaxSize()
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
                        onRegisterGroupsTab()
                        MyGroupScreenContent(
                            activeGroups = uiState.activeGroups,
                            closedGroups = uiState.closedGroups,
                            navigateGroupDetail = onGroupDetailButtonClick,
                            navigateGroupRoom = onGroupRoomButtonClick
                        )
                    }
                    1 -> {
                        onApplyGroupsTab()
                        MyGroupScreenContent(
                            activeGroups = uiState.activeGroups,
                            closedGroups = uiState.closedGroups,
                            navigateGroupDetail = onGroupDetailButtonClick,
                            navigateGroupRoom = onGroupRoomButtonClick
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyGroupScreenPreview() {
    GONGBAEKTheme {
        MyGroupScreen(
            uiState = MyGroupContract.State(),
            onRegisterGroupsTab = {},
            onApplyGroupsTab = {},
            onGroupDetailButtonClick = {},
            onGroupRoomButtonClick = {}
        )
    }
}
