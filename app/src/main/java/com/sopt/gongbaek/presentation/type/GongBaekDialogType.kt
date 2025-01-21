package com.sopt.gongbaek.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sopt.gongbaek.R

enum class GongBaekDialogType(
    @DrawableRes val imageResId: Int,
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int? = null
) {
    REGISTER_SUCCESS(
        titleResId = R.string.dialog_title_register_success,
        imageResId = R.drawable.img_dialog_success
    ),
    REGISTER_FAIL(
        titleResId = R.string.dialog_title_register_fail,
        imageResId = R.drawable.img_dialog_fail
    ),
    ENTER_SUCCESS(
        titleResId = R.string.dialog_title_enter_success,
        imageResId = R.drawable.img_dialog_success,
        descriptionResId = R.string.dialog_description_enter_success
    ),
    ENTER_FAIL(
        titleResId = R.string.dialog_title_enter_fail,
        imageResId = R.drawable.img_dialog_fail,
        descriptionResId = R.string.dialog_description_enter_fail
    ),
    ERROR(
        titleResId = R.string.dialog_title_error,
        imageResId = R.drawable.img_dialog_fail,
        descriptionResId = R.string.dialog_description_error
    )
}
