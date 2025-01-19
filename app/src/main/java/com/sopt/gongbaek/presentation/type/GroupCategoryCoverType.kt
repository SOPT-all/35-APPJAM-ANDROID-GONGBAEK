package com.sopt.gongbaek.presentation.type

import androidx.annotation.DrawableRes
import com.sopt.gongbaek.R

enum class GroupCategoryCoverType(
    @DrawableRes val imageIds: List<Int>
) {
    STUDY(
        imageIds = listOf(
            R.drawable.img_study_1,
            R.drawable.img_study_2,
            R.drawable.img_study_3,
            R.drawable.img_study_4,
            R.drawable.img_study_5,
            R.drawable.img_study_6
        )
    ),
    DINING(
        imageIds = listOf(
            R.drawable.img_dining_1,
            R.drawable.img_dining_2,
            R.drawable.img_dining_3,
            R.drawable.img_dining_4,
            R.drawable.img_dining_5,
            R.drawable.img_dining_6
        )
    ),
    EXERCISE(
        imageIds = listOf(
            R.drawable.img_exercise_1,
            R.drawable.img_exercise_2,
            R.drawable.img_exercise_3,
            R.drawable.img_exercise_4,
            R.drawable.img_exercise_5,
            R.drawable.img_exercise_6
        )
    ),
    PLAYING(
        imageIds = listOf(
            R.drawable.img_playing_1,
            R.drawable.img_playing_2,
            R.drawable.img_playing_3,
            R.drawable.img_playing_4,
            R.drawable.img_playing_5,
            R.drawable.img_playing_6
        )
    ),
    NETWORKING(
        imageIds = listOf(
            R.drawable.img_networking_1,
            R.drawable.img_networking_2,
            R.drawable.img_networking_3,
            R.drawable.img_networking_4,
            R.drawable.img_networking_5,
            R.drawable.img_networking_6
        )
    ),
    OTHERS(
        imageIds = listOf(
            R.drawable.img_others_1,
            R.drawable.img_others_2,
            R.drawable.img_others_3,
            R.drawable.img_others_4,
            R.drawable.img_others_5,
            R.drawable.img_others_6
        )
    )
}
