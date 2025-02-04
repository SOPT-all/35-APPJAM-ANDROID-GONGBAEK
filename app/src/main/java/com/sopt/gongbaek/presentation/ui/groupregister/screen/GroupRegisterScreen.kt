package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.model.GroupRegisterInfo
import com.sopt.gongbaek.presentation.type.GongBaekDialogType
import com.sopt.gongbaek.presentation.type.ImageSelectorType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.dialog.GongBaekDialog
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.GroupPeopleDescription
import com.sopt.gongbaek.presentation.ui.component.section.GroupPlaceDescription
import com.sopt.gongbaek.presentation.ui.component.section.GroupTimeDescription
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.createGroupRegisterTimeDescription
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupRegisterRoute(
    viewModel: GroupRegisterViewModel,
    navigateMyGroup: () -> Unit,
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
                    GroupRegisterContract.SideEffect.NavigateMyGroup -> navigateMyGroup()
                    else -> {}
                }
            }
    }

    GroupRegisterScreen(
        uiState = uiState,
        groupRegisterInfo = uiState.groupRegisterInfo,
        onBackClick = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateBack)
        },
        onRegisterButtonClicked = {
            viewModel.setEvent(
                GroupRegisterContract.Event.OnRegisterButtonClicked(
                    groupRegisterInfo = uiState.groupRegisterInfo
                )
            )
        },
        onDialogConfirmButtonClicked = {
            viewModel.setEvent(GroupRegisterContract.Event.OnDialogConfirmClicked)
        },
        onDialogDismissClicked = {
            viewModel.setEvent(GroupRegisterContract.Event.OnDialogDismissClicked)
        }
    )
}

@Composable
fun GroupRegisterScreen(
    uiState: GroupRegisterContract.State,
    groupRegisterInfo: GroupRegisterInfo,
    onBackClick: () -> Unit,
    onRegisterButtonClicked: () -> Unit,
    onDialogConfirmButtonClicked: () -> Unit,
    onDialogDismissClicked: () -> Unit
) {
    Scaffold(
        bottomBar = {
            GongBaekBasicButton(
                title = stringResource(R.string.groupregister_done),
                enabled = true,
                onClick = onRegisterButtonClicked,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            GroupRegisterSection(
                groupRegisterInfo = groupRegisterInfo,
                onBackClick = onBackClick
            )

            if (uiState.registerState == UiLoadState.Success) {
                Dialog(
                    onDismissRequest = onDialogConfirmButtonClicked
                ) {
                    GongBaekDialog(
                        gongBaekDialogType = GongBaekDialogType.REGISTER_SUCCESS,
                        onConfirmButtonClick = onDialogConfirmButtonClicked
                    )
                }
            } else if (uiState.registerState == UiLoadState.Error) {
                Dialog(
                    onDismissRequest = onDialogConfirmButtonClicked
                ) {
                    GongBaekDialog(
                        gongBaekDialogType = GongBaekDialogType.REGISTER_FAIL,
                        onConfirmButtonClick = onDialogDismissClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun GroupRegisterSection(
    groupRegisterInfo: GroupRegisterInfo,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imageList = ImageSelectorType.getImageListFromCategory(groupRegisterInfo.category)

    val selectedImageResId = if (imageList.isNotEmpty() && groupRegisterInfo.coverImg in 1..imageList.size) {
        imageList[groupRegisterInfo.coverImg - 1]
    } else {
        R.drawable.img_study_1
    }

    Column(
        modifier = modifier
    ) {
        StartTitleTopBar(
            onClick = onBackClick
        )
        GongBaekProgressBar(progressPercent = 0.125f * 8f)

        PageDescriptionSection(
            titleResId = R.string.groupregister_complete_title,
            modifier = Modifier.padding(top = 40.dp, bottom = 24.dp, start = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            item {
                Image(
                    painter = painterResource(selectedImageResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(328f / 273f)
                        .fillMaxWidth()
                )
                Spacer(Modifier.height(26.dp))
            }

            item {
                Text(
                    text = groupRegisterInfo.groupTitle,
                    color = GongBaekTheme.colors.gray10,
                    style = GongBaekTheme.typography.title2.b18,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Column(
                    modifier = Modifier
                        .background(color = GongBaekTheme.colors.gray01, shape = RoundedCornerShape(4.dp))
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    GroupTimeDescription(
                        description = createGroupRegisterTimeDescription(groupRegisterInfo),
                        textStyle = GongBaekTheme.typography.body1.m16,
                        textColor = GongBaekTheme.colors.gray08
                    )

                    GroupPlaceDescription(
                        description = groupRegisterInfo.location,
                        textStyle = GongBaekTheme.typography.body1.m16,
                        textColor = GongBaekTheme.colors.gray08
                    )

                    GroupPeopleDescription(
                        description = stringResource(R.string.groupregister_place_people_count, groupRegisterInfo.maxPeopleCount),
                        textStyle = GongBaekTheme.typography.body1.m16,
                        textColor = GongBaekTheme.colors.gray08
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGroupRegisterScreen() {
    GONGBAEKTheme {
    }
}
