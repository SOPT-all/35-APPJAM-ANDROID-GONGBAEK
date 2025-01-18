package com.sopt.gongbaek.domain.model

data class UserInfo(
    val profileImage: Int?,
    val nickname: String,
    val mbti: String,
    val schoolName: String,
    val schoolMajor: String,
    val schoolGrade: Int?,
    val enterYear: Int?,
    val introduction: String,
    val sex: String,
    val timeTable: TimeTable
)
