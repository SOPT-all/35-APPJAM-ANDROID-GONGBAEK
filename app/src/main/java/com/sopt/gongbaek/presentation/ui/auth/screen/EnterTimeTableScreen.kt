package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.timetable.LectureTimeSelectionTable
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun EnterTimeTableRoute(
    viewModel: AuthViewModel,
    navigateTimetableConvert: () -> Unit,
    navigateBack: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                if (sideEffect is AuthContract.SideEffect.NavigateBack) {
                    navigateBack()
                }
                if (sideEffect is AuthContract.SideEffect.NavigateTimetableConvert) {
                    navigateTimetableConvert()
                }
            }
    }

    EnterTimeTableScreen(
        selectedTimeSlotsByDay = uiState.selectedTimeSlotsByDay,
        navigateTimetableConvert = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateTimetableConvert) },
        navigateBack = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateBack) },
        onTimeSlotSelectionChange = { day, timeSlots ->
            viewModel.setEvent(AuthContract.Event.OnTimeSlotSelectionChange(day, timeSlots))
        }
    )
}

@Composable
private fun EnterTimeTableScreen(
    navigateTimetableConvert: () -> Unit,
    navigateBack: () -> Unit,
    selectedTimeSlotsByDay: Map<String, List<Int>>,
    onTimeSlotSelectionChange: (String, List<Int>) -> Unit
) {
    Scaffold(
        bottomBar = {
            GongBaekBasicButton(
                title = "다음",
                enabled = true,
                onClick = navigateTimetableConvert,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        },
        containerColor = GongBaekTheme.colors.white,
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        content = { paddingValues ->
            EnterTimeTableSection(
                onBackClick = navigateBack,
                selectedTimeSlotsByDay = selectedTimeSlotsByDay,
                onTimeSlotSelectionChange = onTimeSlotSelectionChange,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            )
        }
    )
}

@Composable
private fun EnterTimeTableSection(
    onBackClick: () -> Unit,
    selectedTimeSlotsByDay: Map<String, List<Int>>,
    onTimeSlotSelectionChange: (String, List<Int>) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        StartTitleTopBar(onClick = onBackClick)

        GongBaekProgressBar(progressPercent = 1f)

        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            PageDescriptionSection(
                titleResId = R.string.auth_enter_timetable_title
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.auth_enter_timetable_description))

                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.mainOrange
                        ),
                        start = 0,
                        end = 6
                    )
                },
                color = GongBaekTheme.colors.gray07,
                style = GongBaekTheme.typography.body1.m16
            )

            Spacer(modifier = Modifier.height(14.dp))

            LazyColumn {
                item {
                    val timeSlotLabels = listOf("9", "10", "11", "12", "13", "14", "15", "16", "17")

                    LectureTimeSelectionTable(
                        timeSlotLabels = timeSlotLabels,
                        selectedTimeSlotsByDay = selectedTimeSlotsByDay,
                        onTimeSlotSelectionChange = onTimeSlotSelectionChange
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEnterTimeTableScreen() {
    GONGBAEKTheme {
        EnterTimeTableScreen(
            navigateTimetableConvert = {},
            navigateBack = {},
            selectedTimeSlotsByDay = mapOf(),
            onTimeSlotSelectionChange = { _, _ -> }
        )
    }
}
