package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.type.GroupCycleType
import com.sopt.gongbaek.presentation.type.SelectableButtonType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekSelectableButtons
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupCycleRoute(
    viewModel: GroupRegisterViewModel,
    navigateDay: () -> Unit,
    navigateDayOfWeek: () -> Unit,
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
                    GroupRegisterContract.SideEffect.NavigateDay -> navigateDay()
                    GroupRegisterContract.SideEffect.NavigateDayOfWeek -> navigateDayOfWeek()
                }
            }
    }

    GroupCycleScreen(
        selectedOption = SelectableButtonType.formatCycleOptionToDescription(uiState.groupRegisterInfo.groupType),
        onSelectedOption = { selectedOption ->
            viewModel.setEvent(
                GroupRegisterContract.Event.OnGroupCycleSelected(selectedOption)
            )
        },
        onNextButtonClicked = {
            when (uiState.groupRegisterInfo.groupType) {
                GroupCycleType.ONCE.name -> navigateDay()
                GroupCycleType.WEEKLY.name -> navigateDayOfWeek()
            }
        },
        onBackClick = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateBack)
        }
    )
}

@Composable
fun GroupCycleScreen(
    selectedOption: String,
    onSelectedOption: (String) -> Unit,
    onNextButtonClicked: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GroupCycleSection(
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
private fun GroupCycleSection(
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: String? = null,
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        StartTitleTopBar(onClick = onBackClick)
        GongBaekProgressBar(0.125f)

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            PageDescriptionSection(
                titleResId = R.string.groupregister_cycle_title,
                modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
            )

            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.GROUP_CYCLE,
                onOptionSelected = onOptionSelected,
                selectedOption = selectedOption
            )
            Spacer(Modifier.height(48.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_mark_16),
                    contentDescription = null,
                    tint = GongBaekTheme.colors.mainOrange
                )
                Spacer(Modifier.width(6.dp))

                Text(
                    text = stringResource(R.string.groupregister_cycle_weekly_title),
                    color = GongBaekTheme.colors.mainOrange,
                    style = GongBaekTheme.typography.body2.sb14
                )
            }
            Spacer(Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.groupregister_cycle_weekly_description),
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.caption2.r12
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGroupCycleScreen() {
    GONGBAEKTheme {
        GroupCycleScreen(
            selectedOption = "",
            onSelectedOption = {},
            onNextButtonClicked = {},
            onBackClick = {}
        )
    }
}
