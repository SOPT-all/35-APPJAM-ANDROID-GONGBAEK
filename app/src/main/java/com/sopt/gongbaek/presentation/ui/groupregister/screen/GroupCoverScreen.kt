package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import com.sopt.gongbaek.presentation.type.ImageSelectorType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.button.ImageSelector
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun GroupCoverRoute(
    viewModel: GroupRegisterViewModel,
    navigateGroupPlacePeople: () -> Unit,
    navigateBack: () -> Unit
) {
    val category = "STUDY"

    GroupCoverScreen(
        category = category,
        navigateGroupPlacePeople = navigateGroupPlacePeople,
        navigateBack = navigateBack
    )
}

@Composable
fun GroupCoverScreen(
    category: String,
    navigateGroupPlacePeople: () -> Unit,
    navigateBack: () -> Unit
) {
    var selectedCoverIndex by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GroupCoverSection(
            imageList = ImageSelectorType.getImageListFromCategory(category),
            onBackClick = navigateBack,
            onIndexSelected = {
                selectedCoverIndex = it
            },
            selectedCoverIndex = selectedCoverIndex
        )

        GongBaekBasicButton(
            title = stringResource(R.string.groupregister_next),
            onClick = navigateGroupPlacePeople,
            enabled = selectedCoverIndex != null,
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
            navigateGroupPlacePeople = {},
            navigateBack = {}
        )
    }
}
