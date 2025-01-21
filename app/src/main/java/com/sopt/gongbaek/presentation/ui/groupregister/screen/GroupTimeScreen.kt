package com.sopt.gongbaek.presentation.ui.groupregister.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.timetable.GroupRegisterTimeTable
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupTimeRoute(
    viewModel: GroupRegisterViewModel,
    navigateGroupCategory: () -> Unit,
    navigateBack: () -> Unit
) {
    val timeSlotLabels = listOf("9", "10", "11", "12", "13", "14", "15", "16", "17")
    val selectedDay = "금"

    val lectureTime = mapOf(
        "월" to listOf(0, 1, 2, 3, 4, 5, 6),
        "화" to listOf(7, 8, 9, 10),
        "수" to listOf(0, 1, 2, 3, 4, 5, 6),
        "목" to listOf(3, 4, 5, 6, 7),
        "금" to listOf(0, 1, 2, 3, 9, 10, 11, 12)
    )

    var selectedTimeSlotsByDay by remember { mutableStateOf<Map<String, List<Int>>>(emptyMap()) }

    GroupTimeScreen(
        timeSlotLabels = timeSlotLabels,
        selectedDay = selectedDay,
        lectureTime = lectureTime,
        selectedTimeSlotsByDay = selectedTimeSlotsByDay,
        onTimeSlotSelectionChange = { day, updatedIndices ->
            selectedTimeSlotsByDay = selectedTimeSlotsByDay.toMutableMap().apply {
                this[day] = updatedIndices
            }
        },
        navigateGroupCategory = navigateGroupCategory,
        navigateBack = navigateBack
    )
}

@Composable
fun GroupTimeScreen(
    timeSlotLabels: List<String>,
    selectedDay: String,
    lectureTime: Map<String, List<Int>>,
    selectedTimeSlotsByDay: Map<String, List<Int>>,
    onTimeSlotSelectionChange: (String, List<Int>) -> Unit,
    navigateGroupCategory: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        bottomBar = {
            GongBaekBasicButton(
                title = stringResource(R.string.groupregister_next),
                onClick = navigateGroupCategory,
                enabled = selectedTimeSlotsByDay.values.any { it.isEmpty().not() },
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
                onClick = navigateBack
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
