package com.sopt.gongbaek.presentation.ui.home.component.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.section.GroupTimeDescription
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun NearestGroupSection(
    modifier: Modifier = Modifier
) {
    var columnHeight by remember { mutableIntStateOf(0) }
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_home_main),
            contentDescription = null,
            contentScale = ContentScale.None,
            modifier = Modifier
                .height(with(LocalDensity.current) { columnHeight.toDp() })
        )

        Column(
            modifier = Modifier
                .onGloballyPositioned { layoutCoordinates ->
                    columnHeight = layoutCoordinates.size.height
                }
                .padding(
                    top = 24.dp,
                    bottom = 26.dp
                )
        ) {
            UnivInfo(
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height((LocalConfiguration.current.screenHeightDp * 0.101f).dp))

            NearestGroup(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun UnivInfo(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_school_20),
                contentDescription = null,
                tint = GongBaekTheme.colors.gray05
            )
            Text(
                text = "건국대학교 서울캠퍼스",
                color = GongBaekTheme.colors.gray03,
                style = GongBaekTheme.typography.body2.m14
            )
        }

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_notification_20),
            contentDescription = null,
            tint = GongBaekTheme.colors.white
        )
    }
}

@Composable
private fun NearestGroup(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Text(
                text = "다가오는 모임",
                color = GongBaekTheme.colors.mainOrange,
                style = GongBaekTheme.typography.caption1.sb13,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = "화석의 튜스데이 점심 클럽",
                color = GongBaekTheme.colors.white,
                style = GongBaekTheme.typography.title1.b20,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            GroupTimeDescription(
                description = "12/6 수요일 14시 30분 - 16시 20분",
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.m12
            )
        }

        Box(
            modifier = Modifier
                .background(
                    color = GongBaekTheme.colors.mainOrange,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .align(Alignment.BottomEnd),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "스페이스 입장",
                color = GongBaekTheme.colors.white,
                style = GongBaekTheme.typography.caption2.b12
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNearestGroupSection() {
    NearestGroupSection()
}
