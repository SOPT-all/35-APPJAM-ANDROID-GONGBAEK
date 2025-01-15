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
fun GroupPlaceDescription(
    description: String,
    textColor: Color,
    textStyle: TextStyle
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_place_16),
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
fun GroupPlaceDescriptionPreview() {
    GONGBAEKTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = GongBaekTheme.colors.gray04)
                .padding(20.dp)
        ) {
            // 모임등록 > 등록확인 화면 내 사용
            GroupPlaceDescription(
                description = "세종관 1층 로비",
                textColor = GongBaekTheme.colors.gray08,
                textStyle = GongBaekTheme.typography.body1.m16
            )
            Spacer(modifier = Modifier.height(10.dp))

            // 채우기 메인 화면 내 사용
            GroupPlaceDescription(
                description = "학교 도서관 디지털 랩실에서 만나용",
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.r12
            )
            Spacer(modifier = Modifier.height(10.dp))

            // 모임 상세 화면 내 사용
            GroupPlaceDescription(
                description = "학교 도서관 디지털 랩실에서 만나용",
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.r12
            )
            Spacer(modifier = Modifier.height(10.dp))

            // 나의 채움 화면 내 사용
            GroupPlaceDescription(
                description = "학교 도서관 디지털 랩실에서 만나용",
                textColor = GongBaekTheme.colors.gray06,
                textStyle = GongBaekTheme.typography.caption2.r12
            )
            Spacer(modifier = Modifier.height(10.dp))

            // 모임방 화면 내 사용
            GroupPlaceDescription(
                description = "학교 피아노 앞",
                textColor = GongBaekTheme.colors.white,
                textStyle = GongBaekTheme.typography.caption2.r12
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
