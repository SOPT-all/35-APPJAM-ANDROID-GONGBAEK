package com.sopt.gongbaek.presentation.ui.component.tabpager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicTabPager(tabs: List<String>, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier.fillMaxWidth(),
        containerColor = GongBaekTheme.colors.white,
        contentColor = GongBaekTheme.colors.gray10,
        divider = {},
        indicator = { tabPositions ->
            SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = GongBaekTheme.colors.gray10
            )
        },
        tabs = {
            tabs.forEachIndexed { index, tabTitle ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = tabTitle,
                            style = GongBaekTheme.typography.body1.m16
                        )
                    },
                    selectedContentColor = GongBaekTheme.colors.gray10,
                    unselectedContentColor = GongBaekTheme.colors.gray05
                )
            }
        }
    )
}
