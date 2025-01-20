package com.sopt.gongbaek.presentation.ui.mygroup.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.presentation.type.GroupInfoChipType
import com.sopt.gongbaek.presentation.ui.component.section.GroupInfoSection
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.presentation.util.formatGroupTimeDescription
import com.sopt.gongbaek.presentation.util.extension.roundedBackgroundWithBorder
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun MyGroupScreenContent(
    activeGroups: List<GroupInfo>,
    closedGroups: List<GroupInfo>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            Row(
                modifier = Modifier
                    .padding(top = 28.dp, bottom = 6.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_fire_18),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.my_group_active_group_title),
                    color = GongBaekTheme.colors.gray10,
                    style = GongBaekTheme.typography.title2.sb18
                )
            }
        }
        items(items = activeGroups) { activeGroup ->
            GroupInfoSection(
                groupStatus = GroupInfoChipType.getChipTypeFromStatus(activeGroup.status),
                groupCategory = GroupInfoChipType.getChipTypeFromCategory(activeGroup.category),
                groupCycle = GroupInfoChipType.getChipTypeFromCycle(activeGroup.cycle),
                groupTitle = activeGroup.title,
                groupTime = formatGroupTimeDescription(activeGroup),
                groupPlace = activeGroup.place,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 16.dp)
            )
            GroupInfoSectionButton(
                navigateGroupDetail = {},
                navigateGroupRoom = {},
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = GongBaekTheme.colors.gray02
            )
        }

        item {
            Row(
                modifier = Modifier
                    .padding(top = 28.dp, bottom = 6.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_lock_18),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.my_group_active_group_title),
                    color = GongBaekTheme.colors.gray10,
                    style = GongBaekTheme.typography.title2.sb18
                )
            }
        }
        items(items = closedGroups) { closedGroup ->
            GroupInfoSection(
                groupStatus = GroupInfoChipType.getChipTypeFromStatus(closedGroup.status),
                groupCategory = GroupInfoChipType.getChipTypeFromCategory(closedGroup.category),
                groupCycle = GroupInfoChipType.getChipTypeFromCycle(closedGroup.cycle),
                groupTitle = closedGroup.title,
                groupTime = formatGroupTimeDescription(closedGroup),
                groupPlace = closedGroup.place,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 16.dp)
            )
            GroupInfoSectionButton(
                navigateGroupDetail = {},
                navigateGroupRoom = {},
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = GongBaekTheme.colors.gray02
            )
        }
    }
}

@Composable
private fun GroupInfoSectionButton(
    navigateGroupDetail: () -> Unit,
    navigateGroupRoom: () -> Unit,
    modifier: Modifier = Modifier
) {
    var textHeight by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 4.dp,
                backgroundColor = GongBaekTheme.colors.gray01
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "작성글 보기",
                modifier = Modifier
                    .weight(1f)
                    .clickableWithoutRipple(onClick = navigateGroupDetail)
                    .padding(vertical = 10.dp)
                    .onGloballyPositioned { coordinates ->
                        textHeight = coordinates.size.height
                    },
                color = GongBaekTheme.colors.gray08,
                textAlign = TextAlign.Center,
                style = GongBaekTheme.typography.body2.sb14
            )
            VerticalDivider(
                modifier = Modifier.height(with(LocalDensity.current) { textHeight.toDp() }),
                thickness = 1.dp,
                color = GongBaekTheme.colors.gray03
            )
            Text(
                text = "스페이스 입장하기",
                modifier = Modifier
                    .weight(1f)
                    .clickableWithoutRipple(onClick = navigateGroupRoom)
                    .padding(vertical = 10.dp),
                color = GongBaekTheme.colors.mainOrange,
                textAlign = TextAlign.Center,
                style = GongBaekTheme.typography.body2.sb14
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyGroupScreenContentPreview() {
    GONGBAEKTheme {
        MyGroupScreenContent(
            activeGroups = listOf(
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
            ),
            closedGroups = listOf(
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
