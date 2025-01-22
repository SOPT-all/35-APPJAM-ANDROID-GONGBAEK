package com.sopt.gongbaek.presentation.ui.onboarding.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navigateAuth: () -> Unit
) {
    val pages = listOf(1, 2, 3)
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()
    var backPressedTime by remember { mutableStateOf(0L) }
    val backPressThreshold = 2000
    val context = LocalContext.current
    val onBackClick = {
        if (pagerState.currentPage > 0) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        }
    }

    BackHandler {
        if (pagerState.currentPage == 0) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - backPressedTime <= backPressThreshold) {
                (context as? Activity)?.finish()
            } else {
                backPressedTime = currentTime
            }
        } else {
            onBackClick()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GongBaekTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (pagerState.currentPage == 0) {
            StartTitleTopBar(isLeadingIconIncluded = false)
        } else {
            StartTitleTopBar(onClick = onBackClick)
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> OnboardingScreen1()
                1 -> OnboardingScreen2()
                2 -> OnboardingScreen3()
            }
        }
        Row(
            modifier = Modifier.padding(vertical = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) GongBaekTheme.colors.gray08 else GongBaekTheme.colors.gray03
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color)
                        .size(6.dp)
                )
            }
        }
        GongBaekBasicButton(
            title = if (pagerState.currentPage == pagerState.pageCount - 1) {
                stringResource(R.string.onboarding_button_start)
            } else {
                stringResource(R.string.onboarding_button_next)
            },
            onClick = {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    navigateAuth()
                }
            },
            enabled = true,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen0Preview() {
    GONGBAEKTheme {
        OnboardingScreen(
            navigateAuth = {}
        )
    }
}
