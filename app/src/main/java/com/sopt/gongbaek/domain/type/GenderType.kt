package com.sopt.gongbaek.domain.type

enum class GenderType(
    val description: String
) {
    MAN(
        description = "남자"
    ),
    WOMAN(
        description = "여자"
    );

    companion object {
        fun toGenderType(gender: String): GenderType =
            when (GenderType.entries.find { it.name == gender }) {
                MAN -> MAN
                WOMAN -> WOMAN
                else -> MAN
            }
    }
}
