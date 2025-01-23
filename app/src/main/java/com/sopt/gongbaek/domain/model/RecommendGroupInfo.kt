package com.sopt.gongbaek.domain.model

data class RecommendGroupInfo(
    val groupId: Int = 0,
    val category: String = "",
    val coverImg: Int = 0,
    val profileImg: Int = 0,
    val nickname: String = "",
    val groupType: String = "",
    val groupTitle: String = "",
    val weekDay: String = "",
    val weekDate: String = "",
    val startTime: Double = 0.0,
    val endTime: Double = 0.0,
    val location: String = ""
)
