package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.presentation.ui.groupregister.component.GroupCategorySelectableButtons

@Composable
fun GroupCategoryRoute(
    viewModel: GroupRegisterViewModel,
    navigateGroupCover: () -> Unit,
    navigateBack: () -> Unit
) {
    GroupCategoryScreen(
        navigateGroupCover = navigateGroupCover,
        navigateBack = navigateBack
    )
}

@Composable
fun GroupCategoryScreen(
    navigateGroupCover: () -> Unit,
    navigateBack: () -> Unit
) {
    var selectedOption by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GroupCategorySection(
            selectedOption = selectedOption,
            onOptionSelected = {
                selectedOption = it
            },
            onBackClick = navigateBack
        )

        GongBaekBasicButton(
            title = stringResource(R.string.groupregister_next),
            onClick = navigateGroupCover,
            enabled = selectedOption.isNotBlank(),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .align(Alignment.BottomCenter)

        )
    }
}

@Composable
private fun GroupCategorySection(
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: String? = null,
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        StartTitleTopBar(
            onClick = onBackClick
        )
        GongBaekProgressBar(progressPercent = 0.125f * 4f)

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            PageDescriptionSection(
                titleResId = R.string.groupregister_category_title,
                modifier = Modifier.padding(top = 40.dp, bottom = 24.dp),
                descriptionResId = R.string.groupregister_category_description
            )

            GroupCategorySelectableButtons(
                onOptionSelected = onOptionSelected,
                selectedOption = selectedOption
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGroupCategoryScreen() {
    GroupCategoryScreen(
        navigateGroupCover = {},
        navigateBack = {}
    )
}
