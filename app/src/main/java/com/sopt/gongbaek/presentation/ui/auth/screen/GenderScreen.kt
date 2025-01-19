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
fun GenderRoute(
    viewModel: AuthViewModel,
    navigateSelfIntroduction: () -> Unit,
    navigateBack: () -> Unit
) {
    GenderScreen(
        navigateSelfIntroduction = navigateSelfIntroduction,
        navigateBack = navigateBack
    )
}

@Composable
private fun GenderScreen(
    navigateSelfIntroduction: () -> Unit,
    navigateBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GenderScreenContent(
            onBackClick = navigateBack,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp)
        )

        GongBaekBasicButton(
            title = "다음",
            onClick = navigateSelfIntroduction,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun GenderScreenContent(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        GenderTopBarSection(onBackClick = onBackClick)

        Spacer(modifier = Modifier.height(54.dp))

        GenderSelectionSection(
            modifier = modifier
        )
    }
}

@Composable
private fun GenderTopBarSection(
    onBackClick: () -> Unit
) {
    StartTitleTopBar(onClick = onBackClick)

    GongBaekProgressBar(progressPercent = 0.75f)
}

@Composable
private fun GenderSelectionSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
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

            var selectedOption by remember { mutableStateOf("") }
            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.GENDER,
                options = SelectableButtonType.GENDER.options,
                onOptionSelected = { option -> selectedOption = option },
                selectedOption = selectedOption
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewGenderScreen() {
    GONGBAEKTheme {
        GenderScreen(
            navigateSelfIntroduction = {},
            navigateBack = {}
        )
    }
}
