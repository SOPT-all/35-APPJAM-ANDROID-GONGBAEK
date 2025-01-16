package com.sopt.gongbaek.presentation.type

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.type.CategoryType
import com.sopt.gongbaek.domain.type.DayOfWeekType
import com.sopt.gongbaek.domain.type.GenderType
import com.sopt.gongbaek.domain.type.GradeType
import com.sopt.gongbaek.domain.type.GroupCycleType
import com.sopt.gongbaek.domain.type.MbtiFirstLetterType
import com.sopt.gongbaek.domain.type.MbtiFourthLetterType
import com.sopt.gongbaek.domain.type.MbtiSecondLetterType
import com.sopt.gongbaek.domain.type.MbtiThirdLetterType
import com.sopt.gongbaek.ui.theme.defaultGongBaekColors
import com.sopt.gongbaek.ui.theme.defaultGongBaekTypography

enum class SelectableButtonType(
    val options: List<String>,
    val chunkedCount: Int = 2,
    val typoStyle: TextStyle = defaultGongBaekTypography.body1.m16,
    @ColorRes val selectedButtonColor: Color = defaultGongBaekColors.subOrange,
    @ColorRes val selectedTextColor: Color = defaultGongBaekColors.mainOrange,
    @ColorRes val unSelectedButtonColor: Color = defaultGongBaekColors.gray01,
    @ColorRes val unSelectedTextColor: Color = defaultGongBaekColors.gray07,
    @DrawableRes val categoryImgResIds: List<Int> = emptyList()
) {
    GRADE(
        options = listOf(
            GradeType.FRESHMAN.description,
            GradeType.SOPHOMORE.description,
            GradeType.JUNIOR.description,
            GradeType.SENIOR.description
        )
    ),
    MBTI_FIRST(
        options = listOf(
            MbtiFirstLetterType.E.description,
            MbtiFirstLetterType.I.description
        )
    ),
    MBTI_SECOND(
        options = listOf(
            MbtiSecondLetterType.N.description,
            MbtiSecondLetterType.S.description
        )
    ),
    MBTI_THIRD(
        options = listOf(
            MbtiThirdLetterType.F.description,
            MbtiThirdLetterType.T.description
        )
    ),
    MBTI_FOURTH(
        options = listOf(
            MbtiFourthLetterType.P.description,
            MbtiFourthLetterType.J.description
        )
    ),
    GENDER(
        options = listOf(
            GenderType.MAN.description,
            GenderType.WOMAN.description
        )
    ),
    GROUP_CYCLE(
        options = listOf(
            GroupCycleType.ONCE.description,
            GroupCycleType.WEEKLY.description
        )
    ),
    DAY_OF_WEEK(
        options = listOf(
            DayOfWeekType.MON.description,
            DayOfWeekType.TUE.description,
            DayOfWeekType.WED.description,
            DayOfWeekType.THU.description,
            DayOfWeekType.FRI.description
        ),
        chunkedCount = 1
    ),
    CATEGORY(
        options = listOf(
            CategoryType.STUDY.description,
            CategoryType.DINING.description,
            CategoryType.EXERCISE.description,
            CategoryType.NETWORKING.description,
            CategoryType.PLAYING.description,
            CategoryType.OTHERS.description
        ),
        selectedTextColor = defaultGongBaekColors.gray08,
        unSelectedTextColor = defaultGongBaekColors.gray08,
        categoryImgResIds = listOf(
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample,
            R.drawable.img_fill_list,
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample,
            R.drawable.img_fill_list
        )
    )
}
