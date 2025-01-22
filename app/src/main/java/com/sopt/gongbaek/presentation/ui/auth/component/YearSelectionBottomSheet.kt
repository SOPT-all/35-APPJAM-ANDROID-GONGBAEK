package com.sopt.gongbaek.presentation.ui.auth.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.picker.Picker
import com.sopt.gongbaek.presentation.ui.component.picker.PickerState
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YearSelectionBottomSheet(
    selectedYear: Int,
    currentYear: Int,
    onDismiss: () -> Unit,
    onYearSelected: (Int) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val years = remember { (2000..currentYear).toList() }
    val defaultYearIndex = years.indexOf(
        if (selectedYear == 0) currentYear else selectedYear
    ).takeIf { it >= 0 } ?: 0

    val pickerState = remember {
        PickerState().apply {
            selectedItem = years[defaultYearIndex].toString()
        }
    }

    ModalBottomSheet(
        modifier = Modifier,
        containerColor = GongBaekTheme.colors.white,
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 28.dp)
        ) {
            Text(
                text = "입학년도 선택",
                style = GongBaekTheme.typography.title2.sb18,
                color = GongBaekTheme.colors.black
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 20.dp)
                    .padding(horizontal = 33.dp)
            ) {
                Picker(
                    state = pickerState,
                    items = years.map { it.toString() },
                    initialSelectedIndex = defaultYearIndex,
                    textModifier = Modifier.padding(vertical = 16.dp)
                )
            }

            GongBaekBasicButton(
                title = "선택",
                enabled = true,
                onClick = {
                    val selectedYearValue = pickerState.selectedItem.toIntOrNull() ?: currentYear
                    onYearSelected(selectedYearValue)
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewYearSelectionBottomSheet() {
    YearSelectionBottomSheet(
        selectedYear = 2022,
        currentYear = 2022,
        onDismiss = {},
        onYearSelected = {}
    )
}
