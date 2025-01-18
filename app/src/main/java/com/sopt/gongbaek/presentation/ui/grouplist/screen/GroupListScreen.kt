package com.sopt.gongbaek.presentation.ui.grouplist.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.topbar.CenterTitleTopBar
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import com.sopt.gongbaek.ui.theme.gray02

@Composable
fun GroupListRoute(
    navigateGroupDetail: () -> Unit,
    navigateGroupRegister: () -> Unit
) {
    GroupListScreen(
        navigateGroupDetail = navigateGroupDetail,
        navigateGroupRegister = navigateGroupRegister
    )
}

@Composable
fun GroupListScreen(
    navigateGroupDetail: () -> Unit,
    navigateGroupRegister: () -> Unit
) {
    var selectedDayOfWeekIndex by remember { mutableIntStateOf(0) }
    var selectedCategoryIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterTitleTopBar(R.string.topbar_group)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            DayOfWeekBar(
                selectedIndex = selectedDayOfWeekIndex,
                onIndexSelected = { index ->
                    selectedDayOfWeekIndex = index
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))

            CategoryBar(
                selectedIndex = selectedCategoryIndex,
                onIndexSelected = { index ->
                    selectedCategoryIndex = index
                }
            )
        }
    }
}

@Composable
private fun CategoryBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onIndexSelected: (Int) -> Unit = {}
) {
    val contentLists = listOf(
        R.string.grouplist_dayofweek_all,
        R.string.group_info_chip_category_study,
        R.string.group_info_chip_category_dining,
        R.string.group_info_chip_category_exercise,
        R.string.group_info_chip_category_playing,
        R.string.group_info_chip_category_networking,
        R.string.group_info_chip_category_others
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
            Text(
                text = stringResource(contentLists[index]),
                style = GongBaekTheme.typography.caption1.sb13,
                color = if (selectedIndex == index) {
                    GongBaekTheme.colors.white
                } else {
                    GongBaekTheme.colors.gray06
                },
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
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .clickableWithoutRipple {
                        onIndexSelected(index)
                    }
            )

            if (index == contentLists.size - 1) {
                Spacer(Modifier.width(16.dp))
            }
        }
    }
}

@Composable
private fun DayOfWeekBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onIndexSelected: (Int) -> Unit = {}
) {
    val contentLists = listOf(
        R.string.grouplist_dayofweek_all,
        R.string.grouplist_dayofweek_mon,
        R.string.grouplist_dayofweek_tue,
        R.string.grouplist_dayofweek_wed,
        R.string.grouplist_dayofweek_thu,
        R.string.grouplist_dayofweek_fri
    )
    Row(
        modifier = modifier
            .background(
                color = GongBaekTheme.colors.gray01,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 2.dp, horizontal = 3.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        contentLists.forEachIndexed { index, content ->
            Text(
                text = stringResource(content),
                color = if (selectedIndex == index) {
                    GongBaekTheme.colors.mainOrange
                } else {
                    GongBaekTheme.colors.gray06
                },
                style = GongBaekTheme.typography.caption1.sb13,
                modifier = Modifier
                    .background(
                        color = if (selectedIndex == index) {
                            GongBaekTheme.colors.white
                        } else {
                            GongBaekTheme.colors.gray01
                        },
                        shape = RoundedCornerShape(2.dp)
                    )
                    .padding(horizontal = 9.dp, vertical = 8.dp)
                    .clickableWithoutRipple {
                        onIndexSelected(index)
                    }
            )
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

@Preview
@Composable
private fun PreviewDayOfWeekBar() {
    GONGBAEKTheme {
        DayOfWeekBar()
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGroupListScreen() {
    GONGBAEKTheme {
        GroupListScreen(
            navigateGroupDetail = {},
            navigateGroupRegister = {}
        )
    }
}
