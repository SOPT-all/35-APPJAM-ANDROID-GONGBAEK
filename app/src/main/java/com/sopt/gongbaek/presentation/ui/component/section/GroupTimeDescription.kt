package com.sopt.gongbaek.presentation.ui.component.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupTimeDescription(
    description: String,
    textColor: Color,
    textStyle: TextStyle
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_time_16),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = description,
            color = textColor,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = textStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroupTimeDescriptionPreview() {
    GONGBAEKTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = GongBaekTheme.colors.gray04)
                .padding(20.dp)
        ) {
            // 모임등록 > 등록확인 페이지 내 사용
            GroupTimeDescription(
                description = "매주 수요일 12 - 15시",
                textColor = GongBaekTheme.colors.gray08,
                textStyle = GongBaekTheme.typography.body1.m16
            )
            Spacer(modifier = Modifier.height(10.dp))

            // 채우기 메인 패이지 내 사용
            GroupTimeDescription(
                description = "매주 목요일 13시 - 15시 30분",
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.r12
            )
            Spacer(modifier = Modifier.height(10.dp))

            // 모임상세 페이지 내 사용
            GroupTimeDescription(
                description = "매주 목요일 13시 - 15시 30분",
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.r12
            )
            Spacer(modifier = Modifier.height(10.dp))

            // 나의 채움 페이지 내 사용
            GroupTimeDescription(
                description = "매주 목요일 13시 - 15시 30분",
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.r12
            )
            Spacer(modifier = Modifier.height(10.dp))

            // 모임방 페이지 내 사용
            GroupTimeDescription(
                description = "4/6 목요일 13시 30분 - 15시 30분",
                textColor = GongBaekTheme.colors.white,
                textStyle = GongBaekTheme.typography.caption2.r12
            )
            Spacer(modifier = Modifier.height(10.dp))

            // 홈 > 매주 봐요 추천
            GroupTimeDescription(
                description = "월요일 14-16시",
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.m12
            )
            Spacer(modifier = Modifier.height(10.dp))

            // 홈 > 한번만 봐요 추천
            GroupTimeDescription(
                description = "4/12 수요일 14시 30분 - 15시",
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.m12
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
