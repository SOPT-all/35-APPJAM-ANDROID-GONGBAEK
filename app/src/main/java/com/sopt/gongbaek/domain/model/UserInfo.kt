package com.sopt.gongbaek.domain.model

data class UserInfo(
    val profileImage: Int?,
    val nickname: String,
    val mbti: String,
    val school: String,
    val major: String,
    val grade: Int?,
    val enterYear: Int?,
    val introduction: String,
    val gender: String,
    val timeTable: TimeTable
)
