package com.sopt.gongbaek.domain.type

enum class GenderType(
    val description: String,
    val gender: String
) {
    MAN(
        description = "남자",
        gender = "MAN"
    ),
    WOMAN(
        description = "여자",
        gender = "WOMAN"
    );

    companion object {
        fun toGender(description: String) = when (description) {
            "남자" -> "MAN"
            "여자" -> "WOMAN"
            else -> "UNKNOWN"
        }
    }
}
