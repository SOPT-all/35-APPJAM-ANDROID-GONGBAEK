package com.sopt.gongbaek.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.type.GroupCategoryType

enum class ImageSelectorType(
    @DrawableRes val imageButtonResIdList: List<Int>,
    val chunkedCount: Int
) {
    Profile(
        imageButtonResIdList = listOf(
            R.drawable.img_btn_profile_1,
            R.drawable.img_btn_profile_2,
            R.drawable.img_btn_profile_3,
            R.drawable.img_btn_profile_4,
            R.drawable.img_btn_profile_5,
            R.drawable.img_btn_profile_6
        ),
        chunkedCount = 3
    ),
    Cover(
        imageButtonResIdList = listOf(
            R.drawable.img_study_1,
            R.drawable.img_study_2,
            R.drawable.img_study_3,
            R.drawable.img_study_4,
            R.drawable.img_study_5,
            R.drawable.img_study_6,
            R.drawable.img_dining_1,
            R.drawable.img_dining_2,
            R.drawable.img_dining_3,
            R.drawable.img_dining_4,
            R.drawable.img_dining_5,
            R.drawable.img_dining_6,
            R.drawable.img_exercise_1,
            R.drawable.img_exercise_2,
            R.drawable.img_exercise_3,
            R.drawable.img_exercise_4,
            R.drawable.img_exercise_5,
            R.drawable.img_exercise_6,
            R.drawable.img_playing_1,
            R.drawable.img_playing_2,
            R.drawable.img_playing_3,
            R.drawable.img_playing_4,
            R.drawable.img_playing_5,
            R.drawable.img_playing_6,
            R.drawable.img_networking_1,
            R.drawable.img_networking_2,
            R.drawable.img_networking_3,
            R.drawable.img_networking_4,
            R.drawable.img_networking_5,
            R.drawable.img_networking_6,
            R.drawable.img_others_1,
            R.drawable.img_others_2,
            R.drawable.img_others_3,
            R.drawable.img_others_4,
            R.drawable.img_others_5,
            R.drawable.img_others_6
        ),
        chunkedCount = 2
    );

    companion object {
        private val categoryIndexRanges = mapOf(
            GroupCategoryType.STUDY.name to 0..5,
            GroupCategoryType.DINING.name to 6..11,
            GroupCategoryType.EXERCISE.name to 12..17,
            GroupCategoryType.PLAYING.name to 18..23,
            GroupCategoryType.NETWORKING.name to 24..29,
            GroupCategoryType.OTHERS.name to 30..35
        )

        fun getImageListFromCategory(category: String): List<Int> {
            val range = categoryIndexRanges[category] ?: 0..5
            return Cover.imageButtonResIdList.slice(range)
        }
    }
}
