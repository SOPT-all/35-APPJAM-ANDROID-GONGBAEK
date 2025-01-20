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
import com.sopt.gongbaek.presentation.type.SelectableButtonType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekSelectableButtons
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import timber.log.Timber

@Composable
fun SelectDayOfWeekRoute(
    viewModel: GroupRegisterViewModel,
    navigateGroupTime: () -> Unit,
    navigateBack: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    Timber.d("ㅋㅋㅋ ${uiState.groupRegisterInfo.weekDay}")
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    GroupRegisterContract.SideEffect.NavigateBack -> navigateBack()
                    GroupRegisterContract.SideEffect.NavigateGroupTime -> navigateGroupTime()
                    else -> {}
                }
            }
    }

    SelectDayOfWeekScreen(
        selectedOption = SelectableButtonType.formatDayOfWeekOptionToDescription(uiState.groupRegisterInfo.weekDay),
        onSelectedOption = { selectedOption ->
            viewModel.setEvent(
                GroupRegisterContract.Event.OnDayOfWeekSelected(selectedOption)
            )
        },
        onNextButtonClicked = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateGroupTime)
        },
        onBackClick = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateBack)
        }
    )
}

@Composable
fun SelectDayOfWeekScreen(
    selectedOption: String,
    onSelectedOption: (String) -> Unit,
    onNextButtonClicked: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SelectDayOfWeekSection(
            onBackClick = onBackClick,
            selectedOption = selectedOption,
            onOptionSelected = onSelectedOption
        )

        GongBaekBasicButton(
            title = stringResource(R.string.groupregister_next),
            onClick = onNextButtonClicked,
            enabled = selectedOption.isNotBlank(),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .align(Alignment.BottomCenter)

        )
    }
}

@Composable
private fun SelectDayOfWeekSection(
    onBackClick: () -> Unit,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: String? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        StartTitleTopBar(
            onClick = onBackClick
        )
        GongBaekProgressBar(progressPercent = 0.125f * 2f)

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            PageDescriptionSection(
                titleResId = R.string.groupregister_selectdayofweek_title,
                modifier = Modifier.padding(top = 40.dp, bottom = 60.dp)
            )

            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.DAY_OF_WEEK,
                onOptionSelected = onOptionSelected,
                selectedOption = selectedOption
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSelectDayOfWeekScreen() {
    GONGBAEKTheme {
        SelectDayOfWeekScreen(
            selectedOption = "",
            onSelectedOption = {},
            onNextButtonClicked = {},
            onBackClick = {}
        )
    }
}
