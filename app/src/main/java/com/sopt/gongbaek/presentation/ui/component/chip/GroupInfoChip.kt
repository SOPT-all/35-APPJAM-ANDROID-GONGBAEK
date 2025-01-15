package com.sopt.gongbaek.presentation.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    Column(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
            .padding(10.dp)
    ) {
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.RECRUITING)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.RECRUITED)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.CLOSED)
        Spacer(modifier = Modifier.height(10.dp))

        GroupInfoChip(groupInfoChipType = GroupInfoChipType.STUDY)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.DINING)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.EXERCISE)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.PLAYING)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.NETWORKING)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.OTHERS)
        Spacer(modifier = Modifier.height(10.dp))

        GroupInfoChip(groupInfoChipType = GroupInfoChipType.STUDY_HOME)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.DINING_HOME)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.EXERCISE_HOME)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.PLAYING_HOME)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.NETWORKING_HOME)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.OTHERS_HOME)
        Spacer(modifier = Modifier.height(10.dp))

        GroupInfoChip(groupInfoChipType = GroupInfoChipType.WEEKLY)
        GroupInfoChip(groupInfoChipType = GroupInfoChipType.ONCE)
    }
}
