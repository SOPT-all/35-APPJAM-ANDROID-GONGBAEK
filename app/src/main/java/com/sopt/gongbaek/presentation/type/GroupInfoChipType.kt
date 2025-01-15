package com.sopt.gongbaek.presentation.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.sopt.gongbaek.R
import com.sopt.gongbaek.ui.theme.defaultGongBaekColors

enum class GroupInfoChipType(
    @StringRes val label: Int,
    val backgroundColor: Color = defaultGongBaekColors.subOrange,
    val fontColor: Color = defaultGongBaekColors.mainOrange
) {
    RECRUITING(
        label = R.string.chip_status_recruiting,
        backgroundColor = defaultGongBaekColors.gray08,
        fontColor = defaultGongBaekColors.white
    ),
    RECRUITED(
        label = R.string.chip_status_recruited,
        backgroundColor = defaultGongBaekColors.gray06,
        fontColor = defaultGongBaekColors.white
    ),
    CLOSED(
        label = R.string.chip_status_closed,
        backgroundColor = defaultGongBaekColors.gray02,
        fontColor = defaultGongBaekColors.gray07
    ),
    STUDY(
        label = R.string.chip_category_study
    ),
    DINING(
        label = R.string.chip_category_dining
    ),
    EXERCISE(
        label = R.string.chip_category_exercise
    ),
    PLAYING(
        label = R.string.chip_category_playing
    ),
    NETWORKING(
        label = R.string.chip_category_networking
    ),
    OTHERS(
        label = R.string.chip_category_others
    ),
    STUDY_HOME(
        label = R.string.chip_category_study,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    DINING_HOME(
        label = R.string.chip_category_dining,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    EXERCISE_HOME(
        label = R.string.chip_category_exercise,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    PLAYING_HOME(
        label = R.string.chip_category_playing,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    NETWORKING_HOME(
        label = R.string.chip_category_networking,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    OTHERS_HOME(
        label = R.string.chip_category_others,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    WEEKLY(
        label = R.string.chip_grouptype_weekly,
        backgroundColor = defaultGongBaekColors.gray01,
        fontColor = defaultGongBaekColors.subGreen

    ),
    ONCE(
        label = R.string.chip_grouptype_once,
        backgroundColor = defaultGongBaekColors.gray01,
        fontColor = defaultGongBaekColors.subBlue
    )
}
