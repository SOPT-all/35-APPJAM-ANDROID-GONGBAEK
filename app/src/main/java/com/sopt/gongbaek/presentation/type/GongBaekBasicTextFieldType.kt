package com.sopt.gongbaek.presentation.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.ui.theme.defaultGongBaekColors
import com.sopt.gongbaek.ui.theme.defaultGongBaekTypography

enum class GongBaekBasicTextFieldType(
    @StringRes val title: Int,
    @StringRes val placeholder: Int,
    val maxLength: Int,
    val textFieldTextStyle: TextStyle = defaultGongBaekTypography.body1.m16,
    val placeholderFontColor: Color = defaultGongBaekColors.gray04,
    val textFieldFontColor: Color = defaultGongBaekColors.gray10,
    val singLine: Boolean = false
) {
    NICKNAME(
        title = R.string.gongbaek_basic_textfield_nickname_title,
        placeholder = R.string.gongbaek_basic_textfield_nickname_placeholder,
        maxLength = GongBaekMaxLengths.NICKNAME,
        singLine = true
    ),
    SELF_INTRODUCTION(
        title = R.string.gongbaek_basic_textfield_self_introduction_title,
        placeholder = R.string.gongbaek_basic_textfield_self_introduction_palceholder,
        maxLength = GongBaekMaxLengths.SELF_INTRODUCTION
    ),
    GROUP_PLACE(
        title = R.string.gongbaek_basic_textfield_group_place_title,
        placeholder = R.string.gongbaek_basic_textfield_group_place_placeholder,
        maxLength = GongBaekMaxLengths.GROUP_PLACE,
        singLine = true
    ),
    GROUP_TITLE(
        title = R.string.gongbaek_basic_textfield_group_title_title,
        placeholder = R.string.gongbaek_basic_textfield_group_title_placeholder,
        maxLength = GongBaekMaxLengths.GROUP_TITLE,
        singLine = true
    ),
    GROUP_INTRODUCTION(
        title = R.string.gongbaek_basic_textfield_group_introduction_title,
        placeholder = R.string.gongbaek_basic_textfield_group_introduction_placeholder,
        maxLength = GongBaekMaxLengths.GROUP_INTRODUCTION
    )
}

private object GongBaekMaxLengths {
    const val NICKNAME = 8
    const val SELF_INTRODUCTION = 100
    const val GROUP_PLACE = 20
    const val GROUP_TITLE = 20
    const val GROUP_INTRODUCTION = 100
}
