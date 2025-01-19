package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.type.SelectableButtonType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekSelectableButtons
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import java.util.Calendar

@Composable
fun GradeRoute(
    viewModel: AuthViewModel,
    navigateMbti: () -> Unit,
    navigateBack: () -> Unit
) {
    GradeScreen(
        navigateMbti = navigateMbti,
        navigateBack = navigateBack
    )
}

@Composable
private fun GradeScreen(
    navigateMbti: () -> Unit,
    navigateBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GrandeScreenContent(
            onBackClick = navigateBack,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp)
        )

        GongBaekBasicButton(
            title = "다음",
            onClick = navigateMbti,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun GrandeScreenContent(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        GradeTopBarSection(
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(54.dp))

        GradeSelectionSection(
            modifier = modifier
        )
    }
}

@Composable
private fun GradeTopBarSection(
    onBackClick: () -> Unit
) {
    StartTitleTopBar(
        onClick = onBackClick
    )

    GongBaekProgressBar(progressPercent = 0.5f)
}

@Composable
private fun GradeSelectionSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PageDescriptionSection(
            titleResId = R.string.auth_grade_title,
            descriptionResId = R.string.auth_grade_description
        )

        Spacer(modifier = Modifier.height(44.dp))

        var selectedOption1 by remember { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "학년",
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.body2.sb14
            )
            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.GRADE,
                options = SelectableButtonType.GRADE.options,
                onOptionSelected = { option -> selectedOption1 = option },
                selectedOption = selectedOption1
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        YearPickerDropdown()
    }
}

@Composable
fun YearPickerDropdown() {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    var selectedYear by remember { mutableStateOf<Int?>(null) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "입학년도",
            color = GongBaekTheme.colors.gray08,
            style = GongBaekTheme.typography.body2.sb14
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickableWithoutRipple { expanded = true }
                .border(
                    width = 1.dp,
                    color = GongBaekTheme.colors.gray03,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(
                    vertical = 12.dp,
                    horizontal = 16.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedYear?.toString() ?: (currentYear.toString() + "년"),
                    color = if (selectedYear == null) Color.Gray else Color.Black,
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_bottom_gray_24),
                    contentDescription = null,
                    tint = GongBaekTheme.colors.gray04
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGradeScreen() {
    GONGBAEKTheme {
        GradeScreen(
            navigateMbti = {},
            navigateBack = {}
        )
    }
}
