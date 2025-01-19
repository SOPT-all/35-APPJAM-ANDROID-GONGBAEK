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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun MemberRecommendSection() {
    Column(
        Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "나와 딱 맞는 공백 멤버",
            color = GongBaekTheme.colors.gray10,
            style = GongBaekTheme.typography.title2.b18
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "공백 시간에 이 멤버들과 공백 활동 어때요?",
            color = GongBaekTheme.colors.gray10,
            style = GongBaekTheme.typography.body2.m14
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            RecommendOtherMemberItem()
            RecommendOtherMemberItem()
            RecommendOtherMemberItem()
        }
    }
}

@Composable
fun RecommendOtherMemberItem() {
    Box(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_home_recommend_3),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
            )

            Column(
                modifier = Modifier.padding(vertical = 3.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = "로이임탄",
                    color = GongBaekTheme.colors.gray10,
                    style = GongBaekTheme.typography.body2.sb14
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = GongBaekTheme.colors.subOrange,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 1.dp)
                ) {
                    Text(
                        text = "컴퓨터예술학부",
                        color = GongBaekTheme.colors.mainOrange,
                        style = GongBaekTheme.typography.caption2.m12
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .background(
                    color = GongBaekTheme.colors.mainOrange,
                    shape = RoundedCornerShape(4.dp)
                )
                .align(Alignment.CenterEnd),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "채팅하기",
                color = GongBaekTheme.colors.white,
                style = GongBaekTheme.typography.caption2.b12,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }
    }
    HorizontalDivider(
        thickness = 1.dp,
        color = GongBaekTheme.colors.gray02
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMemberRecommendSection() {
    MemberRecommendSection()
}

@Preview(showBackground = true)
@Composable
private fun PreviewOtherMemberItem() {
    GONGBAEKTheme {
        RecommendOtherMemberItem()
    }
}
