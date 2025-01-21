package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.auth.component.SearchButton
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun UnivMajorRoute(
    viewModel: AuthViewModel,
    navigateGrade: () -> Unit,
    navigateUnivSearch: () -> Unit,
    navigateMajorSearch: () -> Unit,
    navigateBack: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                if (sideEffect is AuthContract.SideEffect.NavigateBack) {
                    navigateBack()
                }
                if (sideEffect is AuthContract.SideEffect.NavigateUnivSearch) {
                    navigateUnivSearch()
                }
                if (sideEffect is AuthContract.SideEffect.NavigateMajorSearch) {
                    navigateMajorSearch()
                }
            }
    }

    UnivMajorScreen(
        univSearchResult = uiState.userInfo.school,
        majorSearchResult = uiState.userInfo.major,
        navigateGrade = navigateGrade,
        onUnivSearchClick = navigateUnivSearch,
        onMajorSearchClick = navigateMajorSearch,
        onBackClick = navigateBack
    )
}

@Composable
private fun UnivMajorScreen(
    univSearchResult: String,
    majorSearchResult: String,
    navigateGrade: () -> Unit,
    onUnivSearchClick: () -> Unit,
    onMajorSearchClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        UnivAndMajorSelectionSection(
            univSearchResult = univSearchResult,
            majorSearchResult = majorSearchResult,
            onBackClick = onBackClick,
            onUnivSearchClick = onUnivSearchClick,
            onMajorSearchClick = onMajorSearchClick,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Center)
        )

        GongBaekBasicButton(
            title = "다음",
            onClick = {
                if (univSearchResult.isNotEmpty() && majorSearchResult.isNotEmpty()) {
                    navigateGrade()
                }
            },
            enabled = univSearchResult.isNotEmpty() && majorSearchResult.isNotEmpty(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun UnivAndMajorSelectionSection(
    univSearchResult: String,
    majorSearchResult: String,
    onBackClick: () -> Unit,
    onUnivSearchClick: () -> Unit,
    onMajorSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        StartTitleTopBar(
            onClick = onBackClick
        )

        GongBaekProgressBar(progressPercent = 0.375f)

        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            PageDescriptionSection(
                titleResId = R.string.auth_univ_major_title,
                descriptionResId = R.string.auth_univ_major_description
            )

            Spacer(modifier = Modifier.height(44.dp))

            SearchButton(
                buttonLabel = "학교",
                searchResult = univSearchResult.ifEmpty { "학교 이름을 검색하세요." },
                isSearched = univSearchResult.isNotEmpty(),
                onSearchButtonClicked = onUnivSearchClick
            )

            Spacer(modifier = Modifier.height(24.dp))

            SearchButton(
                buttonLabel = "학과",
                searchResult = majorSearchResult.ifEmpty { "학과 이름을 검색하세요." },
                isSearched = majorSearchResult.isNotEmpty(),
                onSearchButtonClicked = onMajorSearchClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowUnivMajorScreen() {
    GONGBAEKTheme {
        UnivMajorScreen(
            univSearchResult = "서울대학교",
            majorSearchResult = "",
            navigateGrade = {},
            onUnivSearchClick = {},
            onMajorSearchClick = {},
            onBackClick = {}
        )
    }
}
