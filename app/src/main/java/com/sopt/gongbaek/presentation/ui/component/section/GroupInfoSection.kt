package com.sopt.gongbaek.presentation.ui.component.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.type.GroupInfoChipType
import com.sopt.gongbaek.presentation.ui.component.chip.GroupInfoChip
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupInfoSection(
    groupStatus: GroupInfoChipType,
    groupCategory: GroupInfoChipType,
    groupCycle: GroupInfoChipType,
    groupTitle: String,
    groupTime: String,
    groupPlace: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.background(color = GongBaekTheme.colors.white),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_fill_list),
            contentDescription = null,
            modifier = Modifier
                .width((LocalConfiguration.current.screenWidthDp * 0.28).dp)
                .aspectRatio(1f / 1f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GroupInfoChip(groupInfoChipType = groupStatus)
                GroupInfoChip(groupInfoChipType = groupCategory)
                GroupInfoChip(groupInfoChipType = groupCycle)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = groupTitle,
                color = GongBaekTheme.colors.gray10,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = GongBaekTheme.typography.body1.m16
            )
            Spacer(modifier = Modifier.height(8.dp))
            GroupTimeDescription(
                description = groupTime,
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.r12
            )
            Spacer(modifier = Modifier.height(2.dp))
            GroupPlaceDescription(
                description = groupPlace,
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.r12
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupInfoSectionPreview() {
    GONGBAEKTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            GroupInfoSection(
                groupStatus = GroupInfoChipType.RECRUITING,
                groupCategory = GroupInfoChipType.STUDY,
                groupCycle = GroupInfoChipType.WEEKLY,
                groupTitle = "모각작 하실분~ 여기여기 모여보쇼 싸게싸게 갈라니까",
                groupTime = "매주 목요일 13시 - 15시 30분",
                groupPlace = "학교 도서관 디지털 랩실에서 만나용",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    }
}
