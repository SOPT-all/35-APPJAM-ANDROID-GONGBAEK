package com.sopt.gongbaek.presentation.ui.grouplist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.type.GroupCategoryType
import com.sopt.gongbaek.presentation.type.GroupInfoChipType
import com.sopt.gongbaek.presentation.ui.component.section.GroupInfoSection
import com.sopt.gongbaek.presentation.ui.component.topbar.CenterTitleTopBar
import com.sopt.gongbaek.presentation.ui.grouplist.component.CategoryBar
import com.sopt.gongbaek.presentation.ui.grouplist.component.CycleBottomSheetPresenter
import com.sopt.gongbaek.presentation.ui.grouplist.component.DayOfWeekBar
import com.sopt.gongbaek.presentation.ui.grouplist.component.GongBaekToggleButton
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.presentation.util.formatGroupTimeDescription
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupListRoute(
    viewModel: GroupListViewModel = hiltViewModel(),
    navigateGroupDetail: (Int, String) -> Unit,
    navigateGroupRegister: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(GroupListContract.Event.GetGroups(GroupCategoryType.ALL.name))
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is GroupListContract.SideEffect.NavigateGroupDetail -> {
                        navigateGroupDetail(sideEffect.groupId, sideEffect.groupCycle)
                    }

                    is GroupListContract.SideEffect.NavigateGroupRegister -> navigateGroupRegister()
                }
            }
    }

    GroupListScreen(
        selectedDayOfWeekIndex = uiState.selectedDayOfWeekIndex,
        onDayOfWeekSelected = { index ->
            viewModel.setEvent(GroupListContract.Event.OnDayOfWeekSelected(index))
        },
        selectedCategoryIndex = uiState.selectedCategoryIndex,
        onCategorySelected = { index ->
            viewModel.setEvent(GroupListContract.Event.OnCategorySelected(index))
        },
        toggleCheckedState = uiState.toggleCheckedState,
        onToggleStateChanged = { state ->
            viewModel.setEvent(GroupListContract.Event.OnToggleCheckStateChanged(state))
        },
        navigateGroupDetail = { groupId, groupCycle ->
            viewModel.sendSideEffect(GroupListContract.SideEffect.NavigateGroupDetail(groupId, groupCycle))
        },
        navigateGroupRegister = {
            viewModel.sendSideEffect(GroupListContract.SideEffect.NavigateGroupRegister)
        },
        groupList = uiState.groups
    )
}

@Composable
fun GroupListScreen(
    selectedDayOfWeekIndex: Int,
    onDayOfWeekSelected: (Int) -> Unit,
    selectedCategoryIndex: Int,
    onCategorySelected: (Int) -> Unit,
    toggleCheckedState: Boolean,
    onToggleStateChanged: (Boolean) -> Unit,
    navigateGroupDetail: (Int, String) -> Unit,
    navigateGroupRegister: () -> Unit,
    groupList: List<GroupInfo>
) {
    Scaffold(
        modifier = Modifier
            .padding(top = 10.dp)
            .padding(WindowInsets.navigationBars.asPaddingValues()),
        topBar = {
            CenterTitleTopBar(R.string.topbar_group)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateGroupRegister,
                shape = CircleShape,
                containerColor = GongBaekTheme.colors.mainOrange
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_plus_24),
                    contentDescription = null,
                    tint = GongBaekTheme.colors.white
                )
            }
        },
        containerColor = GongBaekTheme.colors.white
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            DayOfWeekBar(
                selectedIndex = selectedDayOfWeekIndex,
                onIndexSelected = onDayOfWeekSelected
            )
            Spacer(Modifier.height(8.dp))

            CategoryBar(
                selectedIndex = selectedCategoryIndex,
                onIndexSelected = onCategorySelected
            )
            Spacer(Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    HorizontalDivider(
                        thickness = 8.dp,
                        color = GongBaekTheme.colors.gray02
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                            .height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CycleBottomSheetPresenter()

                        GongBaekToggleButton(
                            checkedState = toggleCheckedState,
                            onClick = onToggleStateChanged,
                            modifier = Modifier.align(Alignment.Top)
                        )
                    }
                }

                if (groupList.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.grouplist_empty),
                                textAlign = TextAlign.Center,
                                color = GongBaekTheme.colors.gray06,
                                style = GongBaekTheme.typography.caption1.m13
                            )
                        }
                    }
                } else {
                    items(items = groupList) { groupList ->
                        GroupInfoSection(
                            groupStatus = GroupInfoChipType.getChipTypeFromStatus(groupList.status),
                            groupCategory = GroupInfoChipType.getChipTypeFromCategory(groupList.category),
                            groupCycle = GroupInfoChipType.getChipTypeFromCycle(groupList.cycle),
                            groupCover = groupList.coverImg,
                            groupTitle = groupList.title,
                            groupTime = formatGroupTimeDescription(groupList),
                            groupPlace = groupList.place,
                            modifier = Modifier
                                .padding(vertical = 12.dp, horizontal = 16.dp)
                                .clickableWithoutRipple {
                                    navigateGroupDetail(groupList.groupId, groupList.cycle)
                                }
                        )

                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = GongBaekTheme.colors.gray01
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGroupListScreen() {
}
