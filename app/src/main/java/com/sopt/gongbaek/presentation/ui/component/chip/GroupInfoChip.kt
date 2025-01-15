package com.sopt.gongbaek.presentation.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            .clip(RoundedCornerShape(2.dp))
            .background(color = groupInfoChipType.backgroundColor)
            .padding(horizontal = 4.dp, vertical = 1.dp)
    )
}

@Preview
@Composable
private fun PreviewGroupInfoChip() {
    val status = "RECRUITING"
    val category = "PLAYING"
    val groupType = "ONCE"
    Row(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
            .padding(10.dp)
    ) {
        when (status) {
            "RECRUITING" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.RECRUITING)
            "RECRUITED" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.RECRUITED)
            "CLOSED" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.CLOSED)
            else -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.CLOSED)
        }
        Spacer(modifier = Modifier.width(4.dp))

        when (category) {
            "STUDY" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.STUDY)
            "DINING" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.DINING)
            "EXERCISE" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.EXERCISE)
            "PLAYING" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.PLAYING)
            "NETWORKING" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.NETWORKING)
            "OTHERS" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.OTHERS)
            else -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.OTHERS)
        }
        Spacer(modifier = Modifier.width(4.dp))

        when (groupType) {
            "WEEKLY" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.WEEKLY)
            "ONCE" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.ONCE)
            else -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.WEEKLY)
        }
    }
}

@Preview
@Composable
private fun PreviewHomeGroupInfoChip() {
    val category = "STUDY"

    when (category) {
        "STUDY" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.STUDY_HOME)
        "DINING" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.DINING_HOME)
        "EXERCISE" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.EXERCISE_HOME)
        "PLAYING" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.PLAYING_HOME)
        "NETWORKING" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.NETWORKING_HOME)
        "OTHERS" -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.OTHERS_HOME)
        else -> GroupInfoChip(groupInfoChipType = GroupInfoChipType.OTHERS_HOME)
    }
}
