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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    GapTimeTableScreen(
        navigateCompleteAuth = navigateCompleteAuth,
        navigateEnterTimetable = navigateEnterTimetable
    )
}

@Composable
private fun GapTimeTableScreen(
    navigateCompleteAuth: () -> Unit,
    navigateEnterTimetable: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GongBaekBasicButton(
                    title = "시간표 변경",
                    onClick = navigateEnterTimetable,
                    modifier = Modifier.weight(1f),
                    enableButtonColor = GongBaekTheme.colors.gray09
                )
                GongBaekBasicButton(
                    title = "가입완료",
                    onClick = navigateCompleteAuth,
                    modifier = Modifier.weight(2f)
                )
            }
        },
        containerColor = GongBaekTheme.colors.white,
        content = { paddingValues ->
            GapTimeTableContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            )
        }
    )
}

@Composable
private fun GapTimeTableContent(
    modifier: Modifier = Modifier
) {
    Column {
        GapTimeTableScreenTopBar()

        Spacer(modifier = Modifier.height(54.dp))

        GapTimeTableSection(
            modifier = modifier
        )
    }
}

@Composable
private fun GapTimeTableScreenTopBar() {
    StartTitleTopBar(isLeadingIconIncluded = false)

    GongBaekProgressBar(progressPercent = 1f)
}

@Composable
private fun GapTimeTableSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PageDescriptionSection(
            titleResId = R.string.auth_gap_timetable_title,
            descriptionResId = R.string.auth_gap_timetable_description
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyColumn {
            item {
                val lectureTime = mapOf(
                    "월" to listOf(0, 1, 2, 3, 4, 5, 6),
                    "화" to listOf(7, 8, 9, 10),
                    "수" to listOf(0, 1, 2, 3, 4, 5, 6),
                    "목" to listOf(3, 4, 5, 6, 7),
                    "금" to listOf(0, 1, 2, 3, 9, 10, 11, 12)
                )

                GongBaekTimeTable(
                    lectureTime = lectureTime
                )
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
            navigateEnterTimetable = {}
        )
    }
}
