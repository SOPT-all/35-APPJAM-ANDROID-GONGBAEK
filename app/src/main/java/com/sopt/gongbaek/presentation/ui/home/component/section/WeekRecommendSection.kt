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
import com.sopt.gongbaek.domain.model.RecommendGroupInfo
import com.sopt.gongbaek.presentation.ui.component.section.GroupTimeDescription
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun WeekRecommendSection(
    userNickname: String,
    weekRecommendGroupInfo: List<RecommendGroupInfo>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "공강시간에 정기적인 활동 어때요?",
                color = GongBaekTheme.colors.gray10,
                style = GongBaekTheme.typography.title2.b18
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = userNickname,
                    color = GongBaekTheme.colors.gray06,
                    style = GongBaekTheme.typography.body2.m14
                )
                Text(
                    text = buildAnnotatedString {
                        append("님과 딱 맞는 매주 봐요 모임 추천이에요.")
                        addStyle(
                            style = SpanStyle(
                                color = GongBaekTheme.colors.mainOrange
                            ),
                            start = 7,
                            end = 13
                        )
                    },
                    color = GongBaekTheme.colors.gray06,
                    style = GongBaekTheme.typography.body2.m14
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(weekRecommendGroupInfo.size) { index ->
                WeekRecommendItem(
                    weekRecommendGroupInfo = weekRecommendGroupInfo[index]
                )
            }
        }
    }
}

@Composable
private fun WeekRecommendItem(
    weekRecommendGroupInfo: RecommendGroupInfo
) {
    Column(
        modifier = Modifier
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp
        Image(
            painter = painterResource(id = R.drawable.img_home_small),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(4.dp))
                .width((screenWidth * 0.32f).dp)
                .aspectRatio(116f / 108f)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = weekRecommendGroupInfo.groupTitle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = GongBaekTheme.colors.gray10,
            style = GongBaekTheme.typography.body2.sb14,
            modifier = Modifier.width((screenWidth * 0.32f).dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        GroupTimeDescription(
            description = weekRecommendGroupInfo.weekDate,
            textColor = GongBaekTheme.colors.gray06,
            textStyle = GongBaekTheme.typography.caption2.m12
        )

        Spacer(modifier = Modifier.height(2.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Image(
                painter = painterResource(
                    when (weekRecommendGroupInfo.profileImg) {
                        1 -> R.drawable.img_home_profile_small_1
                        2 -> R.drawable.img_home_profile_small_2
                        3 -> R.drawable.img_home_profile_small_3
                        4 -> R.drawable.img_home_profile_small_4
                        5 -> R.drawable.img_home_profile_small_5
                        6 -> R.drawable.img_home_profile_small_6
                        else -> R.drawable.img_home_profile_small_4
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(vertical = 1.dp)
            )
            Text(
                text = weekRecommendGroupInfo.nickname,
                color = GongBaekTheme.colors.gray09,
                maxLines = 1,
                style = GongBaekTheme.typography.caption2.m12
            )
        }
    }
}

@Preview
@Composable
private fun PreviewWeekSection() {
    WeekRecommendSection(
        userNickname = "김대현",
        weekRecommendGroupInfo = listOf(
            RecommendGroupInfo(
                groupTitle = "스터디 모임",
                nickname = "김대현",
                weekDate = "2021-09-20",
                profileImg = 5
            ),
            RecommendGroupInfo(
                groupTitle = "운동 모임",
                nickname = "김대현1",
                weekDate = "2021-09-20",
                profileImg = 1
            ),
            RecommendGroupInfo(
                groupTitle = "스터디 모임",
                nickname = "김대현2",
                weekDate = "2021-09-20",
                profileImg = 3
            ),
            RecommendGroupInfo(
                groupTitle = "운동 모임",
                nickname = "김대현3",
                weekDate = "2021-09-20"
            )
        )
    )
}
