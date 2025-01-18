package com.sopt.gongbaek.presentation.ui.grouplist.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.presentation.type.GroupInfoChipType
import com.sopt.gongbaek.presentation.ui.component.section.GroupInfoSection
import com.sopt.gongbaek.presentation.ui.component.topbar.CenterTitleTopBar
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.presentation.util.extension.createGroupTimeDescription
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import kotlin.math.roundToInt

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
                onClick = { navigateGroupRegister() },
                shape = CircleShape,
                containerColor = GongBaekTheme.colors.mainOrange
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_plus_24),
                    contentDescription = null,
                    tint = GongBaekTheme.colors.white
                )
            }
        }
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
                            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CycleBottomSheet()

                        GongBaekToggleButton(
                            checkedState = toggleCheckedState,
                            onCLick = {
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
                        groupTime = createGroupTimeDescription(groupList),
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

@Composable
private fun GongBaekToggleButton(
    checkedState: Boolean,
    onCLick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val minBound = with(density) { 0.dp.toPx() }
    val maxBound = with(density) { (LocalConfiguration.current.screenWidthDp * 0.06f).dp.toPx() }
    val state by animateFloatAsState(
        targetValue = if (checkedState) maxBound else minBound,
        animationSpec = tween(durationMillis = 300)
    )
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(
                if (checkedState) {
                    R.string.grouplist_gongbaek_duplicate
                } else {
                    R.string.grouplist_gongbaek_all
                }
            ),
            color = GongBaekTheme.colors.gray06,
            style = GongBaekTheme.typography.caption2.r12
        )
        Spacer(Modifier.width(6.dp))

        Box(
            modifier = modifier
                .aspectRatio(42f / 24f)
                .height((LocalConfiguration.current.screenHeightDp * 0.03f).dp)
                .background(
                    color = if (checkedState) {
                        GongBaekTheme.colors.mainOrange
                    } else {
                        GongBaekTheme.colors.gray06
                    },
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(3.5.dp)
                .clickableWithoutRipple { onCLick() }
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(state.roundToInt(), 0) }
                    .aspectRatio(1f / 1f)
                    .height((LocalConfiguration.current.screenHeightDp * 0.02f).dp)
                    .background(
                        color = GongBaekTheme.colors.white,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
private fun CycleBottomSheet(
    modifier: Modifier = Modifier,
    onCLick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .background(
                color = GongBaekTheme.colors.gray01,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickableWithoutRipple {
                onCLick()
            }
    ) {
        Text(
            text = stringResource(R.string.grouplist_cycle_all),
            color = GongBaekTheme.colors.gray10,
            style = GongBaekTheme.typography.caption1.m13
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_bottom_18),
            contentDescription = null
        )
    }
}

@Composable
private fun CategoryBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onIndexSelected: (Int) -> Unit = {}
) {
    val contentLists = listOf(
        R.string.grouplist_dayofweek_all,
        R.string.group_info_chip_category_study,
        R.string.group_info_chip_category_dining,
        R.string.group_info_chip_category_exercise,
        R.string.group_info_chip_category_playing,
        R.string.group_info_chip_category_networking,
        R.string.group_info_chip_category_others
    )

    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(contentLists.size) { index ->
            if (index == 0) {
                Spacer(Modifier.width(16.dp))
            }

            Box(
                modifier = Modifier
                    .height((LocalConfiguration.current.screenHeightDp * 0.03f).dp)
                    .border(
                        width = 1.dp,
                        color = if (selectedIndex == index) {
                            Color.Transparent
                        } else {
                            GongBaekTheme.colors.gray02
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(
                        color = if (selectedIndex == index) {
                            GongBaekTheme.colors.gray09
                        } else {
                            GongBaekTheme.colors.white
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    )
                    .clickableWithoutRipple {
                        onIndexSelected(index)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(contentLists[index]),
                    style = GongBaekTheme.typography.caption1.m13,
                    color = if (selectedIndex == index) {
                        GongBaekTheme.colors.white
                    } else {
                        GongBaekTheme.colors.gray06
                    }
                )
            }

            if (index == contentLists.size - 1) {
                Spacer(Modifier.width(16.dp))
            }
        }
    }
}

@Composable
private fun DayOfWeekBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onIndexSelected: (Int) -> Unit = {}
) {
    val contentLists = listOf(
        R.string.grouplist_dayofweek_all,
        R.string.grouplist_dayofweek_mon,
        R.string.grouplist_dayofweek_tue,
        R.string.grouplist_dayofweek_wed,
        R.string.grouplist_dayofweek_thu,
        R.string.grouplist_dayofweek_fri
    )
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(
                color = GongBaekTheme.colors.gray01,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 2.dp, horizontal = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        contentLists.forEachIndexed { index, content ->
            Box(
                modifier = modifier
                    .height((LocalConfiguration.current.screenHeightDp * 0.04f).dp)
                    .background(
                        color = if (selectedIndex == index) {
                            GongBaekTheme.colors.white
                        } else {
                            GongBaekTheme.colors.gray01
                        },
                        shape = RoundedCornerShape(2.dp)
                    )
                    .padding(
                        horizontal = if (index == 0) {
                            12.dp
                        } else {
                            9.dp
                        },
                        vertical = 8.dp
                    )
                    .clickableWithoutRipple {
                        onIndexSelected(index)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(content),
                    color = if (selectedIndex == index) {
                        GongBaekTheme.colors.mainOrange
                    } else {
                        GongBaekTheme.colors.gray06
                    },
                    style = GongBaekTheme.typography.caption1.sb13
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCategoryBar() {
    GONGBAEKTheme {
        CategoryBar()
    }
}

@Preview
@Composable
private fun PreviewDayOfWeekBar() {
    GONGBAEKTheme {
        DayOfWeekBar()
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
}
