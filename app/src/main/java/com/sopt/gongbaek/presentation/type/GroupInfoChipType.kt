package com.sopt.gongbaek.presentation.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.type.GroupInfoChipContents
import com.sopt.gongbaek.ui.theme.gray01
import com.sopt.gongbaek.ui.theme.gray02
import com.sopt.gongbaek.ui.theme.gray06
import com.sopt.gongbaek.ui.theme.gray07
import com.sopt.gongbaek.ui.theme.gray08
import com.sopt.gongbaek.ui.theme.gray09
import com.sopt.gongbaek.ui.theme.mainOrange
import com.sopt.gongbaek.ui.theme.subBlue
import com.sopt.gongbaek.ui.theme.subGreen
import com.sopt.gongbaek.ui.theme.subOrange
import com.sopt.gongbaek.ui.theme.white

enum class GroupInfoChipType(
    val chipContents: GroupInfoChipContents,
    @StringRes val label: Int,
    val backgroundColor: Color = subOrange,
    val fontColor: Color = mainOrange
) {
    RECRUITING(
        chipContents = GroupInfoChipContents.STATUS,
        label = R.string.chip_status_recruiting,
        backgroundColor = gray08,
        fontColor = white
    ),
    RECRUITED(
        chipContents = GroupInfoChipContents.STATUS,
        label = R.string.chip_status_recruited,
        backgroundColor = gray06,
        fontColor = white
    ),
    CLOSED(
        chipContents = GroupInfoChipContents.STATUS,
        label = R.string.chip_status_closed,
        backgroundColor = gray02,
        fontColor = gray07
    ),
    STUDY(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_study
    ),
    DINING(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_dining
    ),
    EXERCISE(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_exercise
    ),
    PLAYING(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_playing
    ),
    NETWORKING(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_networking
    ),
    OTHERS(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_others
    ),
    STUDY_HOME(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_study,
        backgroundColor = gray09,
        fontColor = white
    ),
    DINING_HOME(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_dining,
        backgroundColor = gray09,
        fontColor = white
    ),
    EXERCISE_HOME(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_exercise,
        backgroundColor = gray09,
        fontColor = white
    ),
    PLAYING_HOME(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_playing,
        backgroundColor = gray09,
        fontColor = white
    ),
    NETWORKING_HOME(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_networking,
        backgroundColor = gray09,
        fontColor = white
    ),
    OTHERS_HOME(
        chipContents = GroupInfoChipContents.CATEGORY,
        label = R.string.chip_category_others,
        backgroundColor = gray09,
        fontColor = white
    ),
    WEEKLY(
        chipContents = GroupInfoChipContents.GROUPTYPE,
        label = R.string.chip_grouptype_weekly,
        backgroundColor = gray01,
        fontColor = subGreen

    ),
    ONCE(
        chipContents = GroupInfoChipContents.GROUPTYPE,
        label = R.string.chip_grouptype_once,
        backgroundColor = gray01,
        fontColor = subBlue
    )
}
