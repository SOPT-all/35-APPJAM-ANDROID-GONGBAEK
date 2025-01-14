package com.sopt.gongbaek.presentation.type

import androidx.annotation.DrawableRes
import com.sopt.gongbaek.R

enum class ImageSelectorType(
    @DrawableRes val imageButtonResIdList: List<Int>,
    val chunkedCount: Int
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
        chunkedCount = 3
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
        chunkedCount = 2
    )
}
