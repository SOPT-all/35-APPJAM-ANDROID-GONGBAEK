package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun MbtiRoute(
    viewModel: AuthViewModel,
    navigateGender: () -> Unit,
    navigateBack: () -> Unit
) {
    MbtiScreen(
        navigateGender = navigateGender,
        navigateBack = navigateBack
    )
}

@Composable
fun MbtiScreen(
    navigateGender: () -> Unit,
    navigateBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MbtiScreenContent(
            onBackClick = navigateBack,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp)
        )

        GongBaekBasicButton(
            title = "다음",
            onClick = navigateGender,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun MbtiScreenContent(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        MbitTopBarSection(
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(54.dp))

        MbtiSelectionSection(
            modifier = modifier
        )
    }
}

@Composable
private fun MbitTopBarSection(
    onBackClick: () -> Unit
) {
    StartTitleTopBar(onClick = onBackClick)

    GongBaekProgressBar(progressPercent = 0.625f)
}

@Composable
private fun MbtiSelectionSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PageDescriptionSection(
            titleResId = R.string.auth_mbti_title,
            descriptionResId = R.string.auth_mbti_description
        )

        Spacer(modifier = Modifier.height(44.dp))

        var selectedOption1 by remember { mutableStateOf("") }
        var selectedOption2 by remember { mutableStateOf("") }
        var selectedOption3 by remember { mutableStateOf("") }
        var selectedOption4 by remember { mutableStateOf("") }
        Column {
            Text(
                text = "외향형/내향형",
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.body2.sb14
            )
            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.MBTI_FIRST,
                onOptionSelected = { option -> selectedOption1 = option },
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                selectedOption = selectedOption1
            )

            Text(
                text = "감각형/직관형",
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.body2.sb14
            )
            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.MBTI_SECOND,
                onOptionSelected = { option -> selectedOption2 = option },
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                selectedOption = selectedOption2
            )

            Text(
                text = "사고형/감정형",
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.body2.sb14
            )
            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.MBTI_THIRD,
                onOptionSelected = { option -> selectedOption3 = option },
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                selectedOption = selectedOption3
            )

            Text(
                text = "판단형/인식형",
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.body2.sb14
            )
            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.MBTI_FOURTH,
                onOptionSelected = { option -> selectedOption4 = option },
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                selectedOption = selectedOption4
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMbtiScreen() {
    GONGBAEKTheme {
        MbtiScreen(
            navigateGender = {},
            navigateBack = {}
        )
    }
}
