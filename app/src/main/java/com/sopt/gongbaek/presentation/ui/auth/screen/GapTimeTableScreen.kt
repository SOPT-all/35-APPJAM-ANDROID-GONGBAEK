package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.timetable.GongBaekTimeTable
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GapTimeTableRoute(
    viewModel: AuthViewModel,
    navigateCompleteAuth: () -> Unit,
    navigateEnterTimetable: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                if (sideEffect is AuthContract.SideEffect.NavigateCompleteAuth) {
                    navigateCompleteAuth()
                }
                if (sideEffect is AuthContract.SideEffect.NavigateEnterTimetable) {
                    navigateEnterTimetable()
                }
            }
    }

    GapTimeTableScreen(
        lectureTime = uiState.selectedTimeSlotsByDay,
        navigateCompleteAuth = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateCompleteAuth) },
        navigateEnterTimetable = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateEnterTimetable) }
    )
}

@Composable
private fun GapTimeTableScreen(
    navigateCompleteAuth: () -> Unit,
    navigateEnterTimetable: () -> Unit,
    lectureTime: Map<String, List<Int>>
) {
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GongBaekBasicButton(
                    title = "시간표 변경",
                    enabled = true,
                    onClick = navigateEnterTimetable,
                    modifier = Modifier.weight(1f),
                    enableButtonColor = GongBaekTheme.colors.gray09
                )
                GongBaekBasicButton(
                    title = "가입완료",
                    enabled = true,
                    onClick = navigateCompleteAuth,
                    modifier = Modifier.weight(2f)
                )
            }
        },
        containerColor = GongBaekTheme.colors.white,
        content = { paddingValues ->
            GapTimeTableSection(
                lectureTime = lectureTime,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            )
        }
    )
}

@Composable
private fun GapTimeTableSection(
    lectureTime: Map<String, List<Int>>,
    modifier: Modifier = Modifier
) {
    Column {
        StartTitleTopBar(isLeadingIconIncluded = false)

        GongBaekProgressBar(progressPercent = 1f)

        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            PageDescriptionSection(
                titleResId = R.string.auth_gap_timetable_title,
                descriptionResId = R.string.auth_gap_timetable_description
            )

            Spacer(modifier = Modifier.height(14.dp))

            LazyColumn {
                item {
                    GongBaekTimeTable(
                        lectureTime = lectureTime
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewGapTimeTableScreen() {
    GONGBAEKTheme {
        GapTimeTableScreen(
            navigateCompleteAuth = {},
            navigateEnterTimetable = {},
            lectureTime = mapOf(
                "월" to listOf(1, 2, 3, 4, 5),
                "화" to listOf(1, 2, 3, 4, 5),
                "수" to listOf(1, 2, 3, 4, 5),
                "목" to listOf(1, 2, 3, 4, 5),
                "금" to listOf(1, 2, 3, 4, 5)
            )
        )
    }
}
