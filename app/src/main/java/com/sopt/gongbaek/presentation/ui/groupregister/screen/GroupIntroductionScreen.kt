package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.type.GongBaekBasicTextFieldType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.textfield.GongBaekBasicTextField
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun GroupIntroductionRoute(
    viewModel: GroupRegisterViewModel,
    navigateRegister: () -> Unit,
    navigateBack: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    GroupRegisterContract.SideEffect.NavigateBack -> navigateBack()
                    GroupRegisterContract.SideEffect.NavigateRegister -> navigateRegister()
                    else -> {}
                }
            }
    }
    GroupIntroductionScreen(
        groupTitle = uiState.groupRegisterInfo.groupTitle,
        onGroupTitleChange = { groupTitle ->
            viewModel.setEvent(GroupRegisterContract.Event.OnTitleChanged(groupTitle))
        },
        introduction = uiState.groupRegisterInfo.introduction,
        onIntroductionChange = { introduction ->
            viewModel.setEvent(GroupRegisterContract.Event.OnIntroductionChanged(introduction))
        },
        onNextButtonClicked = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateRegister)
        },
        onBackClick = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateBack)
            viewModel.setEvent(GroupRegisterContract.Event.OnTitleIntroductionDeleted)
        }
    )
}

@Composable
fun GroupIntroductionScreen(
    groupTitle: String,
    onGroupTitleChange: (String) -> Unit,
    introduction: String,
    onIntroductionChange: (String) -> Unit,
    onNextButtonClicked: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GroupIntroductionSection(
            groupTitle = groupTitle,
            onGroupTitleChange = onGroupTitleChange,
            introduction = introduction,
            onIntroductionChange = onIntroductionChange,
            onBackClick = onBackClick
        )

        GongBaekBasicButton(
            title = stringResource(R.string.groupregister_next),
            onClick = onNextButtonClicked,
            enabled = groupTitle.isNotBlank() && introduction.isNotBlank(),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun GroupIntroductionSection(
    groupTitle: String,
    onGroupTitleChange: (String) -> Unit,
    introduction: String,
    onIntroductionChange: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        StartTitleTopBar(
            onClick = onBackClick
        )
        GongBaekProgressBar(progressPercent = 0.125f * 7f)

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            PageDescriptionSection(
                titleResId = R.string.groupregister_introduction_title,
                modifier = Modifier.padding(top = 40.dp, bottom = 28.dp)
            )

            GongBaekBasicTextField(
                value = groupTitle,
                onValueChange = onGroupTitleChange,
                gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.GROUP_TITLE
            )
            Spacer(Modifier.height(28.dp))

            GongBaekBasicTextField(
                value = introduction,
                onValueChange = onIntroductionChange,
                gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.GROUP_INTRODUCTION,
                modifier = Modifier
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.169f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGroupIntroductionScreen() {
    GONGBAEKTheme {
    }
}
