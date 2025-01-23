package com.sopt.gongbaek.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserTimeTableResponseDto(
    @SerialName("timeTable")
    val timeTable: List<LectureTimeTable>
)

@Serializable
data class LectureTimeTable(
    @SerialName("idx")
    val idx: Int,
    @SerialName("weekDay")
    val weekDay: String,
    @SerialName("startTime")
    val startTime: Double,
    @SerialName("endTime")
    val endTime: Double
)
