package com.sopt.gongbaek.domain.model

data class UserInfo(
    val profileImage: Int = 0,
    val nickname: String = "",
    val mbti: String = "",
    val school: String = "",
    val major: String = "",
    val grade: Int = 0,
    val enterYear: Int = 0,
    val introduction: String = "",
    val gender: String = "",
    val timeTable: List<TimeTable> = emptyList()
)
