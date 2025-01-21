package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
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
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    GroupRegisterContract.SideEffect.NavigateBack -> navigateBack()
                    GroupRegisterContract.SideEffect.NavigateCover -> navigateGroupCover()
                    else -> {}
                }
            }
    }

    GroupCategoryScreen(
        category = uiState.groupRegisterInfo.category,
        selectedCategory = uiState.selectedCategory,
        onCategorySelected = { category ->
            viewModel.setEvent(
                GroupRegisterContract.Event.OnCategorySelected(category)
            )
        },
        onNextButtonClicked = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateCover)
        },
        onBackClick = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateBack)
        }
    )
}

@Composable
fun GroupCategoryScreen(
    category: String,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    onNextButtonClicked: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GroupCategorySection(
            selectedOption = selectedCategory,
            onOptionSelected = onCategorySelected,
            onBackClick = onBackClick
        )

        GongBaekBasicButton(
            title = stringResource(R.string.groupregister_next),
            onClick = onNextButtonClicked,
            enabled = category.isNotBlank(),
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
        category = "",
        selectedCategory = "",
        onCategorySelected = {},
        onNextButtonClicked = {},
        onBackClick = {}
    )
}
