package com.sopt.gongbaek.presentation.ui.component.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.type.GongBaekBasicTextFieldType
import com.sopt.gongbaek.presentation.util.extension.roundedBackgroundWithBorder
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GongBaekBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    gongBaekBasicTextFieldType: GongBaekBasicTextFieldType,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String = "",
    onErrorChange: (Boolean) -> Unit = {}
) {
    Column {
        TextFieldTitle(
            title = gongBaekBasicTextFieldType.title
        )

        CustomTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            gongBaekBasicTextFieldType = gongBaekBasicTextFieldType,
            isError = isError,
            onErrorChange = onErrorChange
        )

        ErrorAndCharacterCount(
            characterCount = value.length,
            maxCount = gongBaekBasicTextFieldType.maxLength,
            isError = isError,
            errorMessage = errorMessage
        )
    }
}

@Composable
private fun TextFieldTitle(
    @StringRes title: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(title),
            color = GongBaekTheme.colors.gray08,
            style = GongBaekTheme.typography.body2.sb14
        )
    }
}

@Composable
private fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    gongBaekBasicTextFieldType: GongBaekBasicTextFieldType,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    onErrorChange: (Boolean) -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = getTextFieldBorderColor(isError, isFocused)

    val textStyle = gongBaekBasicTextFieldType.textFieldTextStyle.copy(
        color = gongBaekBasicTextFieldType.textFieldFontColor
    )

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue.take(gongBaekBasicTextFieldType.maxLength))
            if (isError) {
                onErrorChange(false)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 6.dp,
                backgroundColor = GongBaekTheme.colors.gray01,
                borderColor = borderColor,
                borderWidth = 1.dp
            )
            .padding(
                horizontal = 16.dp,
                vertical = 14.dp
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        textStyle = textStyle,
        singleLine = gongBaekBasicTextFieldType.singLine
    ) { innerTextField ->
        Box(
            contentAlignment = Alignment.TopStart
        ) {
            if (value.isEmpty()) {
                Text(
                    text = stringResource(gongBaekBasicTextFieldType.placeholder),
                    color = gongBaekBasicTextFieldType.placeholderFontColor,
                    style = gongBaekBasicTextFieldType.textFieldTextStyle
                )
            }
            innerTextField()
        }
    }
}

@Composable
private fun ErrorAndCharacterCount(
    characterCount: Int,
    maxCount: Int,
    isError: Boolean,
    errorMessage: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = if (isError) errorMessage else "",
            modifier = Modifier.weight(1f),
            color = GongBaekTheme.colors.errorRed,
            style = GongBaekTheme.typography.caption2.r12
        )

        Text(
            text = "$characterCount/$maxCount",
            color = GongBaekTheme.colors.gray06,
            style = GongBaekTheme.typography.caption2.r12
        )
    }
}

@Composable
private fun getTextFieldBorderColor(
    isError: Boolean,
    isFocused: Boolean
): Color = when {
    isError -> GongBaekTheme.colors.errorRed
    isFocused -> GongBaekTheme.colors.gray10
    else -> Color.Transparent
}

@Preview(showBackground = true)
@Composable
private fun PreviewExTextField() {
    val text = remember { mutableStateOf("") }
    GongBaekBasicTextField(
        value = text.value,
        onValueChange = { newValue -> text.value = newValue },
        gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.NICKNAME,
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.06f)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewExTextField2() {
    var value by remember { mutableStateOf("공백만") }
    var isError by remember { mutableStateOf(true) }
    val errorMessage by remember { mutableStateOf("중복된 닉네임입니다. 다시 입력해주세요.") }

    GongBaekBasicTextField(
        value = value,
        onValueChange = { newValue -> value = newValue },
        gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.NICKNAME,
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.06f),
        isError = isError,
        errorMessage = errorMessage,
        onErrorChange = { newErrorState -> isError = newErrorState }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewExTextField3() {
    val text = remember { mutableStateOf("") }
    GongBaekBasicTextField(
        value = text.value,
        onValueChange = { newValue -> text.value = newValue },
        gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.SELF_INTRODUCTION,
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.169f)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewExTextField4() {
    val text = remember { mutableStateOf("") }
    GongBaekBasicTextField(
        value = text.value,
        onValueChange = { newValue -> text.value = newValue },
        gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.GROUP_TITLE,
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.06f)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewExTextField5() {
    val text = remember { mutableStateOf("") }
    GongBaekBasicTextField(
        value = text.value,
        onValueChange = { newValue -> text.value = newValue },
        gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.GROUP_INTRODUCTION,
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.169f)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewExTextField6() {
    val text = remember { mutableStateOf("") }
    GongBaekBasicTextField(
        value = text.value,
        onValueChange = { newValue -> text.value = newValue },
        gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.GROUP_PLACE,
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.06f)
    )
}
