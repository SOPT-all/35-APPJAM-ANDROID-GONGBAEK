package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import com.sopt.gongbaek.presentation.type.ImageSelectorType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.button.ImageSelector
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun SelectProfileRoute(
    viewModel: AuthViewModel,
    navigateNickname: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                if (sideEffect is AuthContract.SideEffect.NavigateNickname) {
                    navigateNickname()
                }
            }
    }

    SelectProfileScreen(
        selectedProfile = uiState.userInfo.profileImage,
        onSelectedProfile = { selectedProfileIndex ->
            viewModel.setEvent(
                AuthContract.Event.OnProfileImageSelected(selectedProfileIndex)
            )
        },
        onNextButtonClicked = {
            viewModel.sendSideEffect(AuthContract.SideEffect.NavigateNickname)
        }
    )
}

@Composable
private fun SelectProfileScreen(
    selectedProfile: Int,
    onSelectedProfile: (Int) -> Unit = {},
    onNextButtonClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ImageSelectorSection(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter),
            selectedProfileIndex = selectedProfile,
            onIndexSelected = onSelectedProfile
        )

        GongBaekBasicButton(
            title = "다음",
            onClick = {
                if (selectedProfile != 0) {
                    onNextButtonClicked()
                }
            },
            enabled = selectedProfile != 0,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun ImageSelectorSection(
    selectedProfileIndex: Int,
    onIndexSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        StartTitleTopBar(isLeadingIconIncluded = false)

        GongBaekProgressBar(progressPercent = 0.125f)

        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            PageDescriptionSection(
                titleResId = R.string.auth_select_profile_title,
                descriptionResId = R.string.auth_select_profile_description
            )

            Spacer(modifier = Modifier.height(44.dp))

            ImageSelector(
                imageSelectorType = ImageSelectorType.Profile,
                modifier = Modifier.aspectRatio(1f / 1f),
                selectedIndex = selectedProfileIndex,
                onIndexSelected = onIndexSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSelectProfileScreen() {
    GONGBAEKTheme {
        SelectProfileScreen(
            selectedProfile = 0
        )
    }
}
