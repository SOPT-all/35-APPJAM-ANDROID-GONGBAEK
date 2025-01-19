package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
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

@Composable
fun GroupIntroductionRoute(
    viewModel: GroupRegisterViewModel,
    navigateRegister: () -> Unit,
    naviToBack: () -> Unit
) {
    GroupIntroductionScreen(
        navigateRegister = navigateRegister,
        naviToBack = naviToBack
    )
}

@Composable
fun GroupIntroductionScreen(
    navigateRegister: () -> Unit,
    naviToBack: () -> Unit
) {
    var groupTitle by remember { mutableStateOf("") }
    var introduction by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        GroupIntroductionSection(
            groupTitle = groupTitle,
            onGroupTitleChange = { groupTitle = it },
            introduction = introduction,
            onIntroductionChange = { introduction = it },
            onBackClick = naviToBack
        )

        GongBaekBasicButton(
            title = stringResource(R.string.groupregister_next),
            onClick = navigateRegister,
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
                gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.GROUP_TITLE,
                modifier = Modifier
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.06f)
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
        GroupIntroductionScreen(
            navigateRegister = {},
            naviToBack = {}
        )
    }
}
