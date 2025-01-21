package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    SelfIntroductionScreen(
        navigateEnterTimetable = navigateEnterTimetable,
        navigateBack = navigateBack
    )
}

@Composable
private fun SelfIntroductionScreen(
    navigateEnterTimetable: () -> Unit,
    navigateBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SelfIntroductionSection(
            onBackClick = navigateBack,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp)
        )

        GongBaekBasicButton(
            title = "다음",
            onClick = navigateEnterTimetable,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun SelfIntroductionSection(
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

            val text = remember { mutableStateOf("") }
            GongBaekBasicTextField(
                value = text.value,
                onValueChange = { newValue -> text.value = newValue },
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
            navigateBack = {}
        )
    }
}
