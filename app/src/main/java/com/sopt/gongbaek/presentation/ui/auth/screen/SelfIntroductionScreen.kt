package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
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
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun SelfIntroductionRoute(
    viewModel: AuthViewModel,
    navigateEnterTimetable: () -> Unit,
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
                if (sideEffect is AuthContract.SideEffect.NavigateEnterTimetable) {
                    navigateEnterTimetable()
                }
            }
    }

    SelfIntroductionScreen(
        selfIntroduction = uiState.userInfo.introduction,
        navigateEnterTimetable = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateEnterTimetable) },
        navigateBack = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateBack) },
        onSelfIntroductionChanged = { selfIntroduction -> viewModel.setEvent(AuthContract.Event.OnSelfIntroductionChanged(selfIntroduction)) }
    )
}

@Composable
private fun SelfIntroductionScreen(
    navigateEnterTimetable: () -> Unit,
    navigateBack: () -> Unit,
    selfIntroduction: String,
    onSelfIntroductionChanged: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        SelfIntroductionSection(
            onBackClick = navigateBack,
            selfIntroduction = selfIntroduction,
            onSelfIntroductionChanged = onSelfIntroductionChanged,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp)
        )

        GongBaekBasicButton(
            title = "다음",
            enabled = selfIntroduction.isNotEmpty(),
            onClick = navigateEnterTimetable,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun SelfIntroductionSection(
    selfIntroduction: String,
    onSelfIntroductionChanged: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        StartTitleTopBar(onClick = onBackClick)

        GongBaekProgressBar(progressPercent = 0.875f)

        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            PageDescriptionSection(
                titleResId = R.string.auth_mbti_title
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.auth_self_introduction_description))

                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.mainOrange
                        ),
                        start = 17,
                        end = 27
                    )
                },
                color = GongBaekTheme.colors.gray07,
                style = GongBaekTheme.typography.body1.m16
            )

            Spacer(modifier = Modifier.height(44.dp))

            GongBaekBasicTextField(
                value = selfIntroduction,
                onValueChange = onSelfIntroductionChanged,
                gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.SELF_INTRODUCTION,
                modifier = Modifier
                    .height((LocalConfiguration.current.screenHeightDp * 0.169f).dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSelfIntroductionScreen() {
    GONGBAEKTheme {
        SelfIntroductionScreen(
            navigateEnterTimetable = {},
            navigateBack = {},
            selfIntroduction = "",
            onSelfIntroductionChanged = { }
        )
    }
}
