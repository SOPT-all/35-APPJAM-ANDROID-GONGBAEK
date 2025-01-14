package com.sopt.gongbaek.presentation.type

import androidx.annotation.DrawableRes
import com.sopt.gongbaek.R

enum class SelectImageButtonType(
    @DrawableRes val imageButtonResIdList: List<Int>,
    val chunkedCount: Int,
    val imageHeight: Int
) {
    Profile(
        imageButtonResIdList = listOf(
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample
        ),
        chunkedCount = 3,
        imageHeight = 104
    ),
    Cover(
        imageButtonResIdList = listOf(
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample,
            R.drawable.img_image_button_sample
        ),
        chunkedCount = 2,
        imageHeight = 133
    )
}
