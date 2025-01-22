package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.type.DayOfWeekType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.timetable.GroupRegisterTimeTable
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import timber.log.Timber

@Composable
fun GroupTimeRoute(
    viewModel: GroupRegisterViewModel,
    navigateGroupCategory: () -> Unit,
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
                    GroupRegisterContract.SideEffect.NavigateCategory -> navigateGroupCategory()
                    else -> {}
                }
            }
    }
    val selectedDay = DayOfWeekType.toDayOfWeek(uiState.groupRegisterInfo.weekDay)

    GroupTimeScreen(
        selectedDay = selectedDay,
        lectureTime = uiState.lectureTime,
        selectedTimeSlotsByDay = uiState.selectedTimeSlotsByDay,
        onTimeSlotSelectionChange = { day, updatedIndices ->
            viewModel.setEvent(GroupRegisterContract.Event.OnTimeSlotSelected(day, updatedIndices))
        },
        onNextButtonClicked = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateCategory)
        },
        onBackClick = {
            viewModel.sendSideEffect(GroupRegisterContract.SideEffect.NavigateBack)
            viewModel.setEvent(GroupRegisterContract.Event.OnTimeSlotDeleted)
        }
    )
}

@Composable
fun GroupTimeScreen(
    selectedDay: String,
    lectureTime: Map<String, List<Int>>,
    selectedTimeSlotsByDay: Map<String, List<Int>>,
    onTimeSlotSelectionChange: (String, List<Int>) -> Unit,
    onNextButtonClicked: () -> Unit,
    onBackClick: () -> Unit,
    timeSlotLabels: List<String> = listOf("9", "10", "11", "12", "13", "14", "15", "16", "17")
) {
    Scaffold(
        bottomBar = {
            GongBaekBasicButton(
                title = stringResource(R.string.groupregister_next),
                onClick = onNextButtonClicked,
                enabled = selectedTimeSlotsByDay.values.any { it.isNotEmpty() },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        },
        containerColor = GongBaekTheme.colors.white
    ) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            StartTitleTopBar(
                onClick = onBackClick
            )
            GongBaekProgressBar(progressPercent = 0.125f * 3f)

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                PageDescriptionSection(
                    titleResId = R.string.groupregister_time_title,
                    modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        GroupRegisterTimeTable(
                            timeSlotLabels = timeSlotLabels,
                            selectedDay = selectedDay,
                            selectedTimeSlotsByDay = selectedTimeSlotsByDay,
                            lectureTime = lectureTime,
                            onTimeSlotSelectionChange = onTimeSlotSelectionChange
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGroupTimeScreen() {
    GONGBAEKTheme {
    }
}
