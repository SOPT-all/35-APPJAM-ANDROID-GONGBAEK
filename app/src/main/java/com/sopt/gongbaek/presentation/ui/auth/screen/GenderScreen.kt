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
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekSelectableButtons
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GenderRoute(
    viewModel: AuthViewModel,
    navigateSelfIntroduction: () -> Unit,
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
                if (sideEffect is AuthContract.SideEffect.NavigateSelfIntroduction) {
                    navigateSelfIntroduction()
                }
            }
    }

    GenderScreen(
        gender = uiState.userInfo.gender,
        selectedGender = uiState.selectedGender,
        onGenderSelected = { gender -> viewModel.setEvent(AuthContract.Event.OnGenderSelected(gender)) },
        navigateSelfIntroduction = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateSelfIntroduction) },
        navigateBack = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateBack) }
    )
}

@Composable
private fun GenderScreen(
    gender: String,
    selectedGender: String,
    onGenderSelected: (String) -> Unit,
    navigateSelfIntroduction: () -> Unit,
    navigateBack: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GenderSelectionSection(
            onBackClick = navigateBack,
            selectedGender = selectedGender,
            onSelectedGender = onGenderSelected,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp)
        )

        GongBaekBasicButton(
            title = "다음",
            enabled = gender.isNotEmpty(),
            onClick = { if (gender.isNotEmpty()) navigateSelfIntroduction() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun GenderSelectionSection(
    onBackClick: () -> Unit,
    selectedGender: String,
    onSelectedGender: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        StartTitleTopBar(onClick = onBackClick)

        GongBaekProgressBar(progressPercent = 0.75f)

        Column(
            modifier = modifier,
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            PageDescriptionSection(
                titleResId = R.string.auth_gender_title,
                descriptionResId = R.string.auth_gender_description
            )

            Spacer(modifier = Modifier.height(44.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "성별",
                    color = GongBaekTheme.colors.gray08,
                    style = GongBaekTheme.typography.body2.sb14
                )

                GongBaekSelectableButtons(
                    selectableButtonType = SelectableButtonType.GENDER,
                    options = SelectableButtonType.GENDER.options,
                    onOptionSelected = onSelectedGender,
                    selectedOption = selectedGender
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewGenderScreen() {
    GONGBAEKTheme {
        GenderScreen(
            navigateSelfIntroduction = {},
            navigateBack = {},
            selectedGender = "",
            onGenderSelected = { },
            gender = ""
        )
    }
}
