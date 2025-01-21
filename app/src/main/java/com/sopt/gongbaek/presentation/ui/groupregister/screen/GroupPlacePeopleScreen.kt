package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.sopt.gongbaek.presentation.ui.groupregister.component.GroupPeopleCounter
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import com.sopt.gongbaek.ui.theme.defaultGongBaekTypography

@Composable
fun GroupPlacePeopleRoute(
    viewModel: GroupRegisterViewModel,
    navigateGroupIntroduction: () -> Unit,
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
                    GroupRegisterContract.SideEffect.NavigateIntroduction -> navigateGroupIntroduction()
                    else -> {}
                }
            }
    }

    GroupPlacePeopleScreen(
        place = uiState.groupRegisterInfo.location,
        onPlaceChange = { place ->
            viewModel.setEvent(GroupRegisterContract.Event.OnPlaceChanged(place = place))
        },
        peopleCount = uiState.groupRegisterInfo.maxPeopleCount,
        onIncreasePeopleCount = {
            if (uiState.groupRegisterInfo.maxPeopleCount < 10) {
                viewModel.setEvent(GroupRegisterContract.Event.OnPeopleChanged(uiState.groupRegisterInfo.maxPeopleCount + 1))
            }
        },
        onDecreasePeopleCount = {
            if (uiState.groupRegisterInfo.maxPeopleCount > 2) {
                viewModel.setEvent(GroupRegisterContract.Event.OnPeopleChanged(uiState.groupRegisterInfo.maxPeopleCount - 1))
            }
        },
        onNextButtonClicked = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateIntroduction)
        },
        onBackClick = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateBack)
        }
    )
}

@Composable
fun GroupPlacePeopleScreen(
    place: String,
    onPlaceChange: (String) -> Unit,
    peopleCount: Int,
    onIncreasePeopleCount: () -> Unit,
    onDecreasePeopleCount: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GroupPlacePeopleSection(
            place = place,
            onPlaceChange = onPlaceChange,
            peopleCount = peopleCount,
            onMinusButtonClicked = onDecreasePeopleCount,
            onPlusButtonClicked = onIncreasePeopleCount,
            onBackClick = onBackClick
        )

        GongBaekBasicButton(
            title = stringResource(R.string.groupregister_next),
            onClick = onNextButtonClicked,
            enabled = place.isNotBlank(),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun GroupPlacePeopleSection(
    place: String,
    onPlaceChange: (String) -> Unit,
    peopleCount: Int,
    onMinusButtonClicked: () -> Unit,
    onPlusButtonClicked: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textStyle = defaultGongBaekTypography.body1.m16

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        StartTitleTopBar(
            onClick = onBackClick
        )
        GongBaekProgressBar(progressPercent = 0.125f * 6f)

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            PageDescriptionSection(
                titleResId = R.string.groupregister_place_people_place_title,
                modifier = Modifier.padding(top = 40.dp, bottom = 28.dp)
            )

            GongBaekBasicTextField(
                value = place,
                onValueChange = onPlaceChange,
                gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.GROUP_PLACE,
                modifier = Modifier
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.06f)
            )

            PageDescriptionSection(
                titleResId = R.string.groupregister_place_people_people_title,
                modifier = Modifier.padding(top = 40.dp, bottom = 10.dp)
            )

            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.groupregister_place_people_people_description))
                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.gray07,
                            fontSize = textStyle.fontSize,
                            fontWeight = textStyle.fontWeight,
                            fontFamily = textStyle.fontFamily
                        ),
                        start = 0,
                        end = 5
                    )
                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.mainOrange,
                            fontSize = textStyle.fontSize,
                            fontWeight = textStyle.fontWeight,
                            fontFamily = textStyle.fontFamily
                        ),
                        start = 6,
                        end = 19
                    )
                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.gray07,
                            fontSize = textStyle.fontSize,
                            fontWeight = textStyle.fontWeight,
                            fontFamily = textStyle.fontFamily
                        ),
                        start = 19,
                        end = 30
                    )
                }
            )
            Spacer(Modifier.height(20.dp))

            GroupPeopleCounter(
                peopleCount = peopleCount,
                onMinusButtonClicked = onMinusButtonClicked,
                onPlusButtonClicked = onPlusButtonClicked
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGroupPlacePeopleScreen() {
    GONGBAEKTheme {
    }
}
