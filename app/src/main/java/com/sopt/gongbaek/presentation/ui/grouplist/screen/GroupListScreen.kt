package com.sopt.gongbaek.presentation.ui.grouplist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.presentation.type.GroupInfoChipType
import com.sopt.gongbaek.presentation.ui.component.section.GroupInfoSection
import com.sopt.gongbaek.presentation.ui.component.topbar.CenterTitleTopBar
import com.sopt.gongbaek.presentation.ui.grouplist.component.CategoryBar
import com.sopt.gongbaek.presentation.ui.grouplist.component.CycleBottomSheetPresenter
import com.sopt.gongbaek.presentation.ui.grouplist.component.DayOfWeekBar
import com.sopt.gongbaek.presentation.ui.grouplist.component.GongBaekToggleButton
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.presentation.util.formatGroupTimeDescription
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupListRoute(
    navigateGroupDetail: () -> Unit,
    navigateGroupRegister: () -> Unit
) {
    GroupListScreen(
        navigateGroupDetail = navigateGroupDetail,
        navigateGroupRegister = navigateGroupRegister,
        groupList = listOf(
            GroupInfo(
                groupId = 1,
                coverImg = 1,
                status = "RECRUITING",
                category = "PLAYING",
                cycle = "ONCE",
                title = "화석의 튜스데이 점심 클럽",
                date = "2025-04-06",
                dayOfWeek = "THU",
                startTime = 13.5,
                endTime = 15.5,
                place = "학교 피아노 앞"
            ),
            GroupInfo(
                groupId = 1,
                coverImg = 1,
                status = "RECRUITED",
                category = "PLAYING",
                cycle = "ONCE",
                title = "화석의 튜스데이 점심 클럽",
                date = "2025-04-06",
                dayOfWeek = "THU",
                startTime = 13.5,
                endTime = 15.5,
                place = "학교 피아노 앞"
            ),
            GroupInfo(
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
                place = "학교 피아노 앞"
            ),
            GroupInfo(
                groupId = 1,
                coverImg = 1,
                status = "RECRUITING",
                category = "PLAYING",
                cycle = "ONCE",
                title = "화석의 튜스데이 점심 클럽",
                date = "2025-04-06",
                dayOfWeek = "THU",
                startTime = 13.5,
                endTime = 15.5,
                place = "학교 피아노 앞"
            ),
            GroupInfo(
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
                place = "학교 피아노 앞"
            ),
            GroupInfo(
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
                place = "학교 피아노 앞"
            ),
            GroupInfo(
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
                place = "학교 피아노 앞"
            ),
            GroupInfo(
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
                place = "학교 피아노 앞"
            ),
            GroupInfo(
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
                place = "학교 피아노 앞"
            )
        )
    )
}

@Composable
fun GroupListScreen(
    navigateGroupDetail: () -> Unit,
    navigateGroupRegister: () -> Unit,
    groupList: List<GroupInfo>
) {
    var selectedDayOfWeekIndex by remember { mutableIntStateOf(0) }
    var selectedCategoryIndex by remember { mutableIntStateOf(0) }
    var toggleCheckedState by remember { mutableStateOf(true) }

    Scaffold(
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
                onIndexSelected = { index ->
                    selectedDayOfWeekIndex = index
                }
            )
            Spacer(Modifier.height(8.dp))

            CategoryBar(
                selectedIndex = selectedCategoryIndex,
                onIndexSelected = { index ->
                    selectedCategoryIndex = index
                }
            )
            Spacer(Modifier.height(8.dp))

            HorizontalDivider(
                thickness = 8.dp,
                color = GongBaekTheme.colors.gray02
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
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
                            onClick = {
                                toggleCheckedState = !toggleCheckedState
                            },
                            modifier = Modifier.align(Alignment.Top)
                        )
                    }
                }

                items(items = groupList) { groupList ->
                    GroupInfoSection(
                        groupStatus = GroupInfoChipType.getChipTypeFromStatus(groupList.status),
                        groupCategory = GroupInfoChipType.getChipTypeFromCategory(groupList.category),
                        groupCycle = GroupInfoChipType.getChipTypeFromCycle(groupList.cycle),
                        groupTitle = groupList.title,
                        groupTime = formatGroupTimeDescription(groupList),
                        groupPlace = groupList.place,
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                            .clickableWithoutRipple {
                                navigateGroupDetail()
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

@Preview(showBackground = true)
@Composable
fun ShowGroupListScreen() {
    GONGBAEKTheme {
        GroupListScreen(
            navigateGroupDetail = {},
            navigateGroupRegister = {},
            groupList = listOf(
                GroupInfo(
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
                    place = "학교 피아노 앞"
                ),
                GroupInfo(
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
                    place = "학교 피아노 앞"
                )
            )
        )
    }
}
