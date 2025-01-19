package com.sopt.gongbaek.presentation.ui.mygroup.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.type.MyGroupPagerType
import com.sopt.gongbaek.presentation.ui.component.tabpager.CustomTabPager
import com.sopt.gongbaek.presentation.ui.component.topbar.CenterTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun MyGroupRoute(
    navigateGroupDetail: () -> Unit,
    navigateGroupRoom: () -> Unit
) {
    MyGroupScreen(
        navigateGroupDetail = navigateGroupDetail,
        navigateGroupRoom = navigateGroupRoom
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyGroupScreen(
    navigateGroupDetail: () -> Unit,
    navigateGroupRoom: () -> Unit
) {
    val myGroupTabs: List<String> = MyGroupPagerType.entries.map { it.description }
    val pagerState = rememberPagerState { myGroupTabs.size }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterTitleTopBar(
            centerTitleResId = R.string.my_group_top_bar_title,
            modifier = Modifier.background(color = GongBaekTheme.colors.white)
        )
        CustomTabPager(
            tabs = myGroupTabs,
            pagerState = pagerState
        )
        HorizontalPager(
            state = pagerState,
            pageContent = { page ->
                when (page) {
                    0 -> MyRegisterScreen()
                    1 -> MyApplyScreen()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyGroupScreenPreview() {
    GONGBAEKTheme {
        MyGroupScreen(
            navigateGroupDetail = {},
            navigateGroupRoom = {}
        )
    }
}
