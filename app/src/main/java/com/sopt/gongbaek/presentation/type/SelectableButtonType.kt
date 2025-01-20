package com.sopt.gongbaek.presentation.type

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.type.DayOfWeekType
import com.sopt.gongbaek.domain.type.GenderType
import com.sopt.gongbaek.domain.type.GradeType
import com.sopt.gongbaek.domain.type.GroupCategoryType
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
            GroupCategoryType.STUDY.description,
            GroupCategoryType.DINING.description,
            GroupCategoryType.EXERCISE.description,
            GroupCategoryType.NETWORKING.description,
            GroupCategoryType.PLAYING.description,
            GroupCategoryType.OTHERS.description
        ),
        selectedTextColor = defaultGongBaekColors.gray08,
        unSelectedTextColor = defaultGongBaekColors.gray08,
        categoryImgResIds = listOf(
            R.drawable.img_category_study,
            R.drawable.img_category_dining,
            R.drawable.img_category_exercise,
            R.drawable.img_category_networking,
            R.drawable.img_category_playing,
            R.drawable.img_category_others
        )
    );

    companion object {
        fun formatCycleOptionToDescription(selectedCycle: String): String =
            when (selectedCycle) {
                GroupCycleType.ONCE.name -> GroupCycleType.ONCE.description
                GroupCycleType.WEEKLY.name -> GroupCycleType.WEEKLY.description
                else -> ""
            }

        fun formatDayOfWeekOptionToDescription(selectedDayOfWeek: String): String =
            when (selectedDayOfWeek) {
                DayOfWeekType.MON.name -> DayOfWeekType.MON.description
                DayOfWeekType.TUE.name -> DayOfWeekType.TUE.description
                DayOfWeekType.WED.name -> DayOfWeekType.WED.description
                DayOfWeekType.THU.name -> DayOfWeekType.THU.description
                DayOfWeekType.FRI.name -> DayOfWeekType.FRI.description
                else -> ""
            }

        fun formatCategoryOptionToDescription(category: String): String =
            when (category) {
                GroupCategoryType.STUDY.name -> GroupCategoryType.STUDY.description
                GroupCategoryType.DINING.name -> GroupCategoryType.DINING.description
                GroupCategoryType.EXERCISE.name -> GroupCategoryType.EXERCISE.description
                GroupCategoryType.PLAYING.name -> GroupCategoryType.PLAYING.description
                GroupCategoryType.NETWORKING.name -> GroupCategoryType.NETWORKING.description
                GroupCategoryType.OTHERS.name -> GroupCategoryType.OTHERS.description
                else -> ""
            }
    }
}
