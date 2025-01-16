package com.sopt.gongbaek.presentation.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.type.GroupCategoryType
import com.sopt.gongbaek.domain.type.GroupCycleType
import com.sopt.gongbaek.domain.type.GroupStatusType
import com.sopt.gongbaek.ui.theme.defaultGongBaekColors

enum class GroupInfoChipType(
    @StringRes val label: Int,
    val backgroundColor: Color = defaultGongBaekColors.subOrange,
    val fontColor: Color = defaultGongBaekColors.mainOrange
) {
    RECRUITING(
        label = R.string.group_info_chip_status_recruiting,
        backgroundColor = defaultGongBaekColors.gray08,
        fontColor = defaultGongBaekColors.white
    ),
    RECRUITED(
        label = R.string.group_info_chip_status_recruited,
        backgroundColor = defaultGongBaekColors.gray06,
        fontColor = defaultGongBaekColors.white
    ),
    CLOSED(
        label = R.string.group_info_chip_status_closed,
        backgroundColor = defaultGongBaekColors.gray02,
        fontColor = defaultGongBaekColors.gray07
    ),
    STUDY(
        label = R.string.group_info_chip_category_study
    ),
    DINING(
        label = R.string.group_info_chip_category_dining
    ),
    EXERCISE(
        label = R.string.group_info_chip_category_exercise
    ),
    PLAYING(
        label = R.string.group_info_chip_category_playing
    ),
    NETWORKING(
        label = R.string.group_info_chip_category_networking
    ),
    OTHERS(
        label = R.string.group_info_chip_category_others
    ),
    STUDY_HOME(
        label = R.string.group_info_chip_category_study,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    DINING_HOME(
        label = R.string.group_info_chip_category_dining,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    EXERCISE_HOME(
        label = R.string.group_info_chip_category_exercise,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    PLAYING_HOME(
        label = R.string.group_info_chip_category_playing,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    NETWORKING_HOME(
        label = R.string.group_info_chip_category_networking,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    OTHERS_HOME(
        label = R.string.group_info_chip_category_others,
        backgroundColor = defaultGongBaekColors.gray09,
        fontColor = defaultGongBaekColors.white
    ),
    WEEKLY(
        label = R.string.group_info_chip_group_cycle_weekly,
        backgroundColor = defaultGongBaekColors.gray01,
        fontColor = defaultGongBaekColors.subGreen

    ),
    ONCE(
        label = R.string.group_info_chip_group_cycle_once,
        backgroundColor = defaultGongBaekColors.gray01,
        fontColor = defaultGongBaekColors.subBlue
    ),
    ERROR(
        label = R.string.group_info_chip_error,
        backgroundColor = defaultGongBaekColors.errorRed,
        fontColor = defaultGongBaekColors.white
    );

    companion object {
        fun getChipTypeFromStatus(status: String): GroupInfoChipType =
            when (GroupStatusType.entries.find { it.name == status }) {
                GroupStatusType.RECRUITING -> RECRUITING
                GroupStatusType.RECRUITED -> RECRUITED
                GroupStatusType.CLOSED -> CLOSED
                else -> ERROR
            }


        fun getChipTypeFromCategory(category: String): GroupInfoChipType =
            when (GroupCategoryType.entries.find { it.name == category }) {
                GroupCategoryType.STUDY -> STUDY
                GroupCategoryType.DINING -> DINING
                GroupCategoryType.EXERCISE -> EXERCISE
                GroupCategoryType.PLAYING -> PLAYING
                GroupCategoryType.NETWORKING -> NETWORKING
                GroupCategoryType.OTHERS -> OTHERS
                else -> ERROR
            }

        fun getHomeChipTypeFromCategory(category: String): GroupInfoChipType =
            when (GroupCategoryType.entries.find { it.name == category }) {
                GroupCategoryType.STUDY -> STUDY_HOME
                GroupCategoryType.DINING -> DINING_HOME
                GroupCategoryType.EXERCISE -> EXERCISE_HOME
                GroupCategoryType.PLAYING -> PLAYING_HOME
                GroupCategoryType.NETWORKING -> NETWORKING_HOME
                GroupCategoryType.OTHERS -> OTHERS_HOME
                else -> ERROR
            }

        fun getChipTypeFromCycle(cycle: String): GroupInfoChipType =
            when (GroupCycleType.entries.find { it.name == cycle }) {
                GroupCycleType.ONCE -> ONCE
                GroupCycleType.WEEKLY -> WEEKLY
                else -> ERROR
            }
    }
}
