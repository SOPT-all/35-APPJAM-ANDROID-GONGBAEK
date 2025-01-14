package com.sopt.gongbaek.presentation.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.sopt.gongbaek.ui.theme.defaultGongBaekColors
import com.sopt.gongbaek.ui.theme.defaultGongBaekTypography

enum class GongBaekBasicTextFieldType(
    val title: String,
    val placeholder: String,
    val maxLength: Int,
    val textFieldTextStyle: TextStyle = defaultGongBaekTypography.body1.m16,
    val placeholderFontColor: Color = defaultGongBaekColors.gray04,
    val textFieldFontColor: Color = defaultGongBaekColors.gray10,
    val singLine: Boolean = false
) {
    NICKNAME(
        title = "닉네임",
        placeholder = "한글을 활용해 최대 8자 이내로 입력해주세요.",
        maxLength = GongBaekMaxLengths.NICKNAME,
        singLine = true
    ),
    SELF_INTRODUCTION(
        title = "소개글",
        placeholder = "간단한 소개글을 입력해보세요.\nex) 안녕하세요. 복학한 화석입니다. 사람들을\n좋아하고 함께하는 활동을 좋아해요.",
        maxLength = GongBaekMaxLengths.SELF_INTRODUCTION
    ),
    GROUP_PLACE(
        title = "장소",
        placeholder = "장소를 입력해주세요. ex) M동 1층 로비",
        maxLength = GongBaekMaxLengths.GROUP_PLACE,
        singLine = true
    ),
    GROUP_TITLE(
        title = "제목",
        placeholder = "제목을 입력해주세요.",
        maxLength = GongBaekMaxLengths.GROUP_TITLE,
        singLine = true
    ),
    GROUP_INTRODUCTION(
        title = "소개글",
        placeholder = "간단한 소개글을 20자 이상 입력해보세요.\nex. 화석된 사람들끼리 소소한 점심 모임 어때요?",
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
