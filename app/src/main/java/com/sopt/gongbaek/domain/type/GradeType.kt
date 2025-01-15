package com.sopt.gongbaek.domain.type

enum class GradeType(
    val description: String,
    val grade: Int
) {
    FRESHMAN(
        description = "1학년",
        grade = 1
    ),
    SOPHOMORE(
        description = "2학년",
        grade = 2
    ),
    JUNIOR(
        description = "3학년",
        grade = 3
    ),
    SENIOR(
        description = "4학년",
        grade = 4
    );

    companion object {
        fun toGrade(description: String) = when (description) {
            "1학년" -> 1
            "2학년" -> 2
            "3학년" -> 3
            "4학년" -> 4
            else -> 0
        }
    }
}
