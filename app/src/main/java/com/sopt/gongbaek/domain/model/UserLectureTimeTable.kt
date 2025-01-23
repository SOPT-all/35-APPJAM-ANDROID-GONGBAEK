package com.sopt.gongbaek.domain.model

data class UserLectureTimeTable(
    val idx: Int = 0,
    val weekDay: String = "",
    val startTime: Double = 0.0,
    val endTime: Double = 0.0
)
