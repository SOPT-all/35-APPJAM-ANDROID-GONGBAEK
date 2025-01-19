package com.sopt.gongbaek.domain.model

data class GroupRegisterInfo(
    val groupType: String,
    val weekDate: String,
    val weekDay: String,
    val startTime: Double,
    val endTime: Double,
    val dueDate: String,
    val category: String,
    val coverImg: Int,
    val location: String,
    val maxPeopleCount: Int,
    val groupTitle: String,
    val introduction: String
)
