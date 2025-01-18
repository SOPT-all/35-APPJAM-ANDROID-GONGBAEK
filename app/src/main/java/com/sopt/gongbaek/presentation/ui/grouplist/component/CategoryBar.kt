package com.sopt.gongbaek.presentation.ui.grouplist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.domain.type.GroupCategoryType
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun CategoryBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onIndexSelected: (Int) -> Unit = {}
) {
    val contentLists = listOf(
        GroupCategoryType.ALL.description,
        GroupCategoryType.STUDY.description,
        GroupCategoryType.DINING.description,
        GroupCategoryType.EXERCISE.description,
        GroupCategoryType.PLAYING.description,
        GroupCategoryType.NETWORKING.description,
        GroupCategoryType.OTHERS.description,
    )

    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(contentLists.size) { index ->
            if (index == 0) {
                Spacer(Modifier.width(16.dp))
            }

            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = if (selectedIndex == index) {
                            Color.Transparent
                        } else {
                            GongBaekTheme.colors.gray02
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(
                        color = if (selectedIndex == index) {
                            GongBaekTheme.colors.gray09
                        } else {
                            GongBaekTheme.colors.white
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    )
                    .clickableWithoutRipple {
                        onIndexSelected(index)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = contentLists[index],
                    style = GongBaekTheme.typography.caption1.m13,
                    color = if (selectedIndex == index) {
                        GongBaekTheme.colors.white
                    } else {
                        GongBaekTheme.colors.gray06
                    }
                )
            }

            if (index == contentLists.size - 1) {
                Spacer(Modifier.width(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCategoryBar() {
    GONGBAEKTheme {
        CategoryBar()
    }
}
