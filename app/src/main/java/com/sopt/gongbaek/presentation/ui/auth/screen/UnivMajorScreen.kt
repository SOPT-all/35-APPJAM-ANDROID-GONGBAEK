package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    navigateBack: () -> Unit
) {
    UnivMajorScreen(
        navigateGrade = navigateGrade,
        navigateBack = navigateBack
    )
}

@Composable
private fun UnivMajorScreen(
    navigateGrade: () -> Unit,
    navigateBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        UnivAndMajorSelectionSection(
            onBackClick = navigateBack,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Center)
        )

        GongBaekBasicButton(
            title = "다음",
            onClick = navigateGrade,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun UnivAndMajorSelectionSection(
    onBackClick: () -> Unit,
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
                searchResult = "학교 이름을 검색하세요.",
                onSearchButtonClicked = { }
            )

            Spacer(modifier = Modifier.height(24.dp))

            SearchButton(
                buttonLabel = "학과",
                searchResult = "학과 이름을 검색하세요.",
                onSearchButtonClicked = { }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowUnivMajorScreen() {
    GONGBAEKTheme {
        UnivMajorScreen(
            navigateGrade = {},
            navigateBack = {}
        )
    }
}
