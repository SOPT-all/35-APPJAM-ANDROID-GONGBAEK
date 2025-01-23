package com.sopt.gongbaek.presentation.ui.home.component.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.section.GroupTimeDescription
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun OnceRecommendSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "한번만 만나도 특별할 우리",
                color = GongBaekTheme.colors.gray10,
                style = GongBaekTheme.typography.title2.b18
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = buildAnnotatedString {
                    append("한번만 봐요 모임으로 잊지 못할 추억을 만들어보세요!")
                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.mainOrange
                        ),
                        start = 0,
                        end = 7
                    )
                },
                color = GongBaekTheme.colors.gray06,
                style = GongBaekTheme.typography.body2.m14
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(5) {
                OnceRecommendItem()
            }
        }
    }
}

@Composable
private fun OnceRecommendItem() {
    Column(
        modifier = Modifier
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp
        Image(
            painter = painterResource(id = R.drawable.img_home_medium),
            contentDescription = "null",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(4.dp))
                .width((screenWidth * 0.43f).dp)
                .aspectRatio(156f / 104f)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "시험 전 하루 벼락치기 해요...",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = GongBaekTheme.colors.gray10,
            style = GongBaekTheme.typography.body2.sb14,
            modifier = Modifier.width((screenWidth * 0.43f).dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        GroupTimeDescription(
            description = "4/12일 수요일 14시 반 -15시",
            textColor = GongBaekTheme.colors.gray06,
            textStyle = GongBaekTheme.typography.caption2.m12
        )

        Spacer(modifier = Modifier.height(2.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_home_profile_small_4),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(vertical = 1.dp)
            )
            Text(
                text = "로이임탄",
                color = GongBaekTheme.colors.gray09,
                maxLines = 1,
                style = GongBaekTheme.typography.caption2.m12
            )
        }
    }
}

@Preview
@Composable
private fun PreviewOnceRecommendSection() {
    OnceRecommendSection()
}
