package com.sopt.gongbaek.domain.type

enum class CategoryType(
    val description: String,
    val category: String
) {
    STUDY(
        description = "스터디",
        category = "STUDY"
    ),
    DINING(
        description = "식사",
        category = "DINING"
    ),
    EXERCISE(
        description = "운동/산책",
        category = "EXERCISE"
    ),
    NETWORKING(
        description = "네트워킹",
        category = "NETWORKING"
    ),
    PLAYING(
        description = "취미/오락",
        category = "PLAYING"
    ),
    OTHERS(
        description = "기타",
        category = "OTHERS"
    );

    companion object {
        fun toCategory(description: String) = when (description) {
            "스터디" -> "STUDY"
            "식사" -> "DINING"
            "운동/산책" -> "EXERCISE"
            "네트워킹" -> "NETWORKING"
            "취미/오락" -> "PLAYING"
            "기타" -> "OTHERS"
            else -> ""
        }
    }
}