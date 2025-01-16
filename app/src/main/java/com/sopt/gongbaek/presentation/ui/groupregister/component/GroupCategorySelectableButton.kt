package com.sopt.gongbaek.presentation.ui.groupregister.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.type.SelectableButtonType
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.presentation.util.extension.roundedBackgroundWithBorder
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun GroupCategorySelectableButtons(
    textOptions: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: String? = null
) {
    val selectableButtonType = SelectableButtonType.CATEGORY
    val chunkedOptions = textOptions.chunked(size = selectableButtonType.chunkedCount)

    Column {
        chunkedOptions.forEachIndexed { rowIndex, rowOptions ->
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                rowOptions.forEachIndexed { optionIndex, option ->
                    GroupCategorySelectableButton(
                        textOption = option,
                        onClick = { onOptionSelected(option) },
                        isSelected = option == selectedOption,
                        modifier = Modifier.weight(1f),
                        imageOption = selectableButtonType.categoryImgResIds[rowIndex * selectableButtonType.chunkedCount + optionIndex]
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun GroupCategorySelectableButton(
    textOption: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    imageOption: Int
) {
    val selectableButtonType = SelectableButtonType.CATEGORY

    Column(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 6.dp,
                backgroundColor = if (isSelected) selectableButtonType.selectedButtonColor else selectableButtonType.unSelectedButtonColor
            )
            .clickableWithoutRipple(onClick = onClick)
            .padding(horizontal = 41.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageOption),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1f / 1f)
                .padding(bottom = 10.dp)
        )

        Text(
            text = textOption,
            color = if (isSelected) selectableButtonType.selectedTextColor else selectableButtonType.unSelectedTextColor,
            style = selectableButtonType.typoStyle
        )
    }
}

@Preview
@Composable
private fun PreviewGroupCategorySelectableButton() {
    GONGBAEKTheme {
        var selectedOption by remember { mutableStateOf("") }
        GroupCategorySelectableButtons(
            textOptions = SelectableButtonType.CATEGORY.options,
            onOptionSelected = { option -> selectedOption = option },
            selectedOption = selectedOption
        )
    }
}
