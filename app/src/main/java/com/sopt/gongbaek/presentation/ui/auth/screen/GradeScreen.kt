package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
import com.sopt.gongbaek.presentation.type.SelectableButtonType
import com.sopt.gongbaek.presentation.ui.auth.component.YearSelectableButton
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekSelectableButtons
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GradeRoute(
    viewModel: AuthViewModel,
    navigateMbti: () -> Unit,
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
                if (sideEffect is AuthContract.SideEffect.NavigateMbti) {
                    navigateMbti()
                }
            }
    }

    GradeScreen(
        grade = uiState.userInfo.grade,
        enterYear = uiState.userInfo.enterYear,
        selectedGrade = uiState.selectedGrade,
        onGradeSelected = { grade -> viewModel.setEvent(AuthContract.Event.OnGradeSelected(grade)) },
        onYearSelected = { year -> viewModel.setEvent(AuthContract.Event.OnYearSelected(year)) },
        onBackClick = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateBack) },
        navigateMbti = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateMbti) }
    )
}

@Composable
private fun GradeScreen(
    grade: Int,
    selectedGrade: String,
    enterYear: Int,
    onGradeSelected: (String) -> Unit,
    onYearSelected: (Int) -> Unit,
    navigateMbti: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GrandeSelectionSection(
            onBackClick = onBackClick,
            selectedGrade = selectedGrade,
            onGradeSelected = onGradeSelected,
            selectedYear = enterYear,
            onYearSelected = onYearSelected,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp)
        )

        GongBaekBasicButton(
            title = "다음",
            enabled = grade != 0 && enterYear != 0,
            onClick = navigateMbti,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun GrandeSelectionSection(
    onBackClick: () -> Unit,
    selectedGrade: String,
    onGradeSelected: (String) -> Unit,
    selectedYear: Int,
    onYearSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        StartTitleTopBar(onClick = onBackClick)

        GongBaekProgressBar(progressPercent = 0.5f)

        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            PageDescriptionSection(
                titleResId = R.string.auth_grade_title,
                descriptionResId = R.string.auth_grade_description
            )

            Spacer(modifier = Modifier.height(44.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "학년",
                    color = GongBaekTheme.colors.gray08,
                    style = GongBaekTheme.typography.body2.sb14
                )
                GongBaekSelectableButtons(
                    selectableButtonType = SelectableButtonType.GRADE,
                    options = SelectableButtonType.GRADE.options,
                    onOptionSelected = onGradeSelected,
                    selectedOption = selectedGrade
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            YearSelectableButton(
                selectedYear = selectedYear,
                onYearSelected = onYearSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreViewGradeScreen() {
    GONGBAEKTheme {
        GradeScreen(
            navigateMbti = {},
            onBackClick = {},
            grade = 0,
            enterYear = 0,
            onGradeSelected = {},
            selectedGrade = "",
            onYearSelected = {}
        )
    }
}
