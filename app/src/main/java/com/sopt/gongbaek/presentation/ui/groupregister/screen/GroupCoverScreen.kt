package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import timber.log.Timber

@Composable
fun GroupCoverRoute(
    viewModel: GroupRegisterViewModel,
    navigateGroupPlacePeople: () -> Unit,
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
                    GroupRegisterContract.SideEffect.NavigateGroupPlacePeople -> navigateGroupPlacePeople()
                    else -> {}
                }
            }
    }

    GroupCoverScreen(
        category = uiState.groupRegisterInfo.category,
        cover = uiState.groupRegisterInfo.coverImg,
        selectedCover = uiState.selectedCover,
        onCoverSelected = { cover ->
            viewModel.setEvent(
                GroupRegisterContract.Event.OnCoverSelected(cover = cover)
            )
        },
        onNextButtonClicked = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateGroupPlacePeople)
        },
        onBackClick = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateBack)
        }
    )
}

@Composable
fun GroupCoverScreen(
    category: String,
    cover: Int,
    selectedCover: Int?,
    onCoverSelected: (Int) -> Unit,
    onNextButtonClicked: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GroupCoverSection(
            imageList = ImageSelectorType.getImageListFromCategory(category),
            onBackClick = onBackClick,
            onIndexSelected = onCoverSelected,
            selectedCoverIndex = selectedCover
        )

        GongBaekBasicButton(
            title = stringResource(R.string.groupregister_next),
            onClick = onNextButtonClicked,
            enabled = cover != 0,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun GroupCoverSection(
    imageList: List<Int>,
    onBackClick: () -> Unit,
    onIndexSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedCoverIndex: Int? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        StartTitleTopBar(
            onClick = onBackClick
        )
        GongBaekProgressBar(progressPercent = 0.125f * 5f)

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            PageDescriptionSection(
                titleResId = R.string.groupregister_cover_title,
                modifier = Modifier.padding(top = 40.dp, bottom = 24.dp),
                descriptionResId = R.string.groupregister_cover_description
            )

            ImageSelector(
                imageSelectorType = ImageSelectorType.Cover,
                modifier = Modifier
                    .aspectRatio(16f / 13f),
                imageButtonResIdList = imageList,
                selectedAlpha = 0.6f,
                selectedIndex = selectedCoverIndex,
                onIndexSelected = onIndexSelected
            )
        }
    }
}

@Preview
@Composable
fun ShowGroupCoverScreen() {
    val category = "EXERCISE"
    GONGBAEKTheme {
        GroupCoverScreen(
            category = category,
            cover = 1,
            selectedCover = 0,
            onCoverSelected = {},
            onNextButtonClicked = {},
            onBackClick = {}
        )
    }
}
