package com.sopt.gongbaek.presentation.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.type.SelectableButtonType
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.presentation.util.extension.createMbti
import com.sopt.gongbaek.presentation.util.extension.roundedBackgroundWithBorder
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import timber.log.Timber

@Composable
fun GongBaekSelectableButtons(
    selectableButtonType: SelectableButtonType,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: String? = null
) {
    val chunkedOptions = options.chunked(size = selectableButtonType.chunkedCount)

    Column {
        chunkedOptions.forEach { rowOptions ->
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                rowOptions.forEach { option ->
                    GongBaekSelectableButton(
                        selectableButtonType = selectableButtonType,
                        option = option,
                        onClick = { onOptionSelected(option) },
                        isSelected = option == selectedOption,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            if (selectableButtonType == SelectableButtonType.DAY_OF_WEEK) {
                Spacer(modifier = Modifier.height(16.dp))
            } else {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun GongBaekSelectableButton(
    selectableButtonType: SelectableButtonType,
    option: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 6.dp,
                backgroundColor = if (isSelected) selectableButtonType.selectedButtonColor else selectableButtonType.unSelectedButtonColor
            )
            .clickableWithoutRipple(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = option,
            modifier = Modifier.padding(vertical = 14.dp),
            color = if (isSelected) selectableButtonType.selectedTextColor else selectableButtonType.unSelectedTextColor,
            style = selectableButtonType.typoStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GongBaekSelectableButtonsPreview1() {
    // 학년 선택
    GONGBAEKTheme {
        var selectedOption1 by remember { mutableStateOf("") }
        GongBaekSelectableButtons(
            selectableButtonType = SelectableButtonType.GRADE,
            options = SelectableButtonType.GRADE.options,
            onOptionSelected = { option -> selectedOption1 = option },
            selectedOption = selectedOption1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GongBaekSelectableButtonsPreview2() {
    // MBTI 선택
    GONGBAEKTheme {
        var selectedOption1 by remember { mutableStateOf("") }
        var selectedOption2 by remember { mutableStateOf("") }
        var selectedOption3 by remember { mutableStateOf("") }
        var selectedOption4 by remember { mutableStateOf("") }
        val mbti = createMbti(selectedOption1, selectedOption2, selectedOption3, selectedOption4)
        Column {
            Text(
                text = "외향형/내향형",
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.body2.sb14
            )
            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.MBTI_FIRST,
                options = SelectableButtonType.MBTI_FIRST.options,
                onOptionSelected = { option -> selectedOption1 = option },
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                selectedOption = selectedOption1
            )

            Text(
                text = "감각형/직관형",
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.body2.sb14
            )
            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.MBTI_SECOND,
                options = SelectableButtonType.MBTI_SECOND.options,
                onOptionSelected = { option -> selectedOption2 = option },
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                selectedOption = selectedOption2
            )

            Text(
                text = "사고형/감정형",
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.body2.sb14
            )
            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.MBTI_THIRD,
                options = SelectableButtonType.MBTI_THIRD.options,
                onOptionSelected = { option -> selectedOption3 = option },
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                selectedOption = selectedOption3
            )

            Text(
                text = "판단형/인식형",
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.body2.sb14
            )
            GongBaekSelectableButtons(
                selectableButtonType = SelectableButtonType.MBTI_FOURTH,
                options = SelectableButtonType.MBTI_FOURTH.options,
                onOptionSelected = { option -> selectedOption4 = option },
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                selectedOption = selectedOption4
            )
        }
        Timber.tag("mbti").d(mbti)
    }
}

@Preview(showBackground = true)
@Composable
private fun GongBaekSelectableButtonsPreview3() {
    GONGBAEKTheme {
        // 성별 선택
        var selectedOption by remember { mutableStateOf("") }
        GongBaekSelectableButtons(
            selectableButtonType = SelectableButtonType.GENDER,
            options = SelectableButtonType.GENDER.options,
            onOptionSelected = { option -> selectedOption = option },
            selectedOption = selectedOption
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GongBaekSelectableButtonsPreview4() {
    GONGBAEKTheme {
        // 활동주기 선택
        var selectedOption by remember { mutableStateOf("") }
        GongBaekSelectableButtons(
            selectableButtonType = SelectableButtonType.GROUP_CYCLE,
            options = SelectableButtonType.GROUP_CYCLE.options,
            onOptionSelected = { option -> selectedOption = option },
            selectedOption = selectedOption
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GongBaekSelectableButtonsPreview5() {
    GONGBAEKTheme {
        // 요일 선택
        var selectedOption by remember { mutableStateOf("") }
        GongBaekSelectableButtons(
            selectableButtonType = SelectableButtonType.DAY_OF_WEEK,
            options = SelectableButtonType.DAY_OF_WEEK.options,
            onOptionSelected = { option -> selectedOption = option },
            selectedOption = selectedOption
        )
    }
}
