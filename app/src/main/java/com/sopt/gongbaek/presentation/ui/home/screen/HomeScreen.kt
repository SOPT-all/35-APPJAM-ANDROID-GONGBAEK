package com.sopt.gongbaek.presentation.ui.home.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.ui.home.component.section.HomeBannerSection
import com.sopt.gongbaek.presentation.ui.home.component.section.MemberRecommendSection
import com.sopt.gongbaek.presentation.ui.home.component.section.NearestGroupSection
import com.sopt.gongbaek.presentation.ui.home.component.section.OnceSection
import com.sopt.gongbaek.presentation.ui.home.component.section.WeekSection
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            NearestGroupSection()
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            WeekSection()
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            OnceSection()
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            HomeBannerSection()
            Spacer(modifier = Modifier.height(28.dp))
        }

        item {
            MemberRecommendSection()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowHomeScreen() {
    GONGBAEKTheme {
        HomeScreen()
    }
}
