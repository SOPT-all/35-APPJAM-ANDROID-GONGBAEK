package com.sopt.gongbaek.presentation.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.type.GroupInfoChipType
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupInfoChip(
    groupInfoChipType: GroupInfoChipType,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(groupInfoChipType.label),
        color = groupInfoChipType.fontColor,
        modifier = modifier
            .background(
                color = groupInfoChipType.backgroundColor,
                shape = RoundedCornerShape(2.dp)
            )
            .padding(horizontal = 4.dp, vertical = 1.dp),
        style = GongBaekTheme.typography.caption2.r12
    )
}

@Preview
@Composable
private fun PreviewGroupInfoChip() {
    val status = GroupInfoChipType.RECRUITING
    val category = GroupInfoChipType.PLAYING
    val groupType = GroupInfoChipType.ONCE
    Row(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        GroupInfoChip(groupInfoChipType = status)
        GroupInfoChip(groupInfoChipType = category)
        GroupInfoChip(groupInfoChipType = groupType)
    }
}

@Preview
@Composable
private fun PreviewHomeGroupInfoChip() {
    val category = GroupInfoChipType.STUDY_HOME

    GroupInfoChip(groupInfoChipType = category)
}
