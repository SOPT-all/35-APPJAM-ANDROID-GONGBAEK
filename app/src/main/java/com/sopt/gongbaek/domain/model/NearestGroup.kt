package com.sopt.gongbaek.domain.model

data class NearestGroup(
    val groupId: Int = 0,
    val category: String = "",
    val groupType: String = "",
    val groupTitle: String = "",
    val weekDay: String = "",
    val weekDate: String = "",
    val currentPeopleCount: Int = 0,
    val maxPeopleCount: Int = 0,
    val startTime: Double = 0.0,
    val endTime: Double = 0.0,
    val location: String = ""
)
