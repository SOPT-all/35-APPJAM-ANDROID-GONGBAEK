package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    navigateGapTimetable: () -> Unit,
    navigateBack: () -> Unit
) {
    EnterTimeTableScreen(
        navigateGapTimetable = navigateGapTimetable,
        navigateBack = navigateBack
    )
}

@Composable
private fun EnterTimeTableScreen(
    navigateGapTimetable: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        bottomBar = {
            GongBaekBasicButton(
                title = "다음",
                onClick = navigateGapTimetable,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        },
        containerColor = GongBaekTheme.colors.white,
        content = { paddingValues ->
            EnterTimeTableSection(
                onBackClick = navigateBack,
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
                    val selectedTimeSlotsByDay = remember { mutableStateOf(mapOf<String, List<Int>>()) }

                    LectureTimeSelectionTable(
                        timeSlotLabels = timeSlotLabels,
                        selectedTimeSlotsByDay = selectedTimeSlotsByDay.value,
                        onTimeSlotSelectionChange = { day, updatedIndices ->
                            selectedTimeSlotsByDay.value += (day to updatedIndices)
                        }
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
            navigateGapTimetable = {},
            navigateBack = {}
        )
    }
}
