package com.sopt.gongbaek.domain.type

enum class GroupStatusType(
    val description: String,
) {
    RECRUITING(
        description = "모집중",
    ),
    RECRUITED(
        description = "인원마감",
    ),
    CLOSED(
        description = "종료",
    )
}