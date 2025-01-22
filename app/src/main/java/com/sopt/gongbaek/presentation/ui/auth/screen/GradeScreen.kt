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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.type.SelectableButtonType
import com.sopt.gongbaek.presentation.ui.auth.component.picker.Picker
import com.sopt.gongbaek.presentation.ui.auth.component.picker.PickerState
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
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                if (sideEffect is AuthContract.SideEffect.NavigateBack) {
                    navigateBack()
                }
                if (sideEffect is AuthContract.SideEffect.NavigateMbti) {
                    navigateMbti()
                }
            }
    }

    GradeScreen(
        navigateMbti = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateMbti) },
        onBackClick = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateBack) },
        grade = uiState.userInfo.grade,
        enterYear = uiState.userInfo.enterYear,
        selectedGrade = uiState.selectedGrade,
        onGradeSelected = { grade -> viewModel.setEvent(AuthContract.Event.OnGradeSelected(grade)) },
        onYearSelected = { year -> viewModel.setEvent(AuthContract.Event.OnYearSelected(year)) }
    )
}

@Composable
private fun GradeScreen(
    navigateMbti: () -> Unit,
    onBackClick: () -> Unit,
    grade: Int,
    enterYear: Int,
    selectedGrade: String,
    onGradeSelected: (String) -> Unit,
    onYearSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GrandeSelectionSection(
            onBackClick = onBackClick,
            selectedGrade = selectedGrade,
            onGradeSelected = onGradeSelected,
            selectedYear = enterYear,
            onYearSelected = onYearSelected,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 16.dp)
        )

        GongBaekBasicButton(
            title = "다음",
            enabled = grade != 0 && enterYear != 0,
            onClick = navigateMbti,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun GrandeSelectionSection(
    onBackClick: () -> Unit,
    selectedGrade: String,
    onGradeSelected: (String) -> Unit,
    selectedYear: Int,
    onYearSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        StartTitleTopBar(onClick = onBackClick)

        GongBaekProgressBar(progressPercent = 0.5f)

        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            PageDescriptionSection(
                titleResId = R.string.auth_grade_title,
                descriptionResId = R.string.auth_grade_description
            )

            Spacer(modifier = Modifier.height(44.dp))

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
                    onOptionSelected = onGradeSelected,
                    selectedOption = selectedGrade
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            YearPickerDropdown(
                selectedYear = selectedYear,
                onYearSelected = onYearSelected
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YearPickerDropdown(
    selectedYear: Int,
    onYearSelected: (Int) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

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
                .clickableWithoutRipple { showBottomSheet = true }
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
                    text = if (selectedYear == 0) "${currentYear}년" else "${selectedYear}년",
                    color = if (selectedYear == 0) GongBaekTheme.colors.gray04 else GongBaekTheme.colors.gray10,
                    style = GongBaekTheme.typography.body1.m16
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_bottom_gray_24),
                    contentDescription = null,
                    tint = GongBaekTheme.colors.gray04
                )
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier,
                    containerColor = GongBaekTheme.colors.white,
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState
                ) {
                    Column(
                        Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = "입학년도 선택",
                            style = GongBaekTheme.typography.title2.sb18,
                            color = GongBaekTheme.colors.black
                        )

                        val years = remember { (2000..currentYear).toList() }
                        val defaultYearIndex = years.indexOf(
                            if (selectedYear == 0) currentYear else selectedYear
                        ).takeIf { it >= 0 } ?: 0

                        val valuesPickerState = remember {
                            PickerState().apply {
                                selectedItem = years[defaultYearIndex].toString()
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(49.dp)
                        ) {
                            Picker(
                                state = valuesPickerState,
                                items = years.map { it.toString() },
                                initialSelectedIndex = defaultYearIndex,
                                textModifier = Modifier.padding(vertical = 16.dp)
                            )
                        }

                        GongBaekBasicButton(
                            title = "선택",
                            enabled = true,
                            onClick = {
                                showBottomSheet = false
                                val selectedYear = valuesPickerState.selectedItem.toIntOrNull() ?: 0
                                if (selectedYear != 0) onYearSelected(selectedYear)
                            }
                        )
                    }
                }
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
            onBackClick = {},
            grade = 0,
            enterYear = 0,
            onGradeSelected = {},
            selectedGrade = "",
            onYearSelected = {}
        )
    }
}
