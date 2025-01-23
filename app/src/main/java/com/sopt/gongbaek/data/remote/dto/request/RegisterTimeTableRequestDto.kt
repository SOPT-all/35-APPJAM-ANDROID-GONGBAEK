package com.sopt.gongbaek.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterTimeTableRequestDto(
    @SerialName("weekDay")
    val weekDay: String,
    @SerialName("startTime")
    val startTime: Double,
    @SerialName("endTime")
    val endTime: Double
)
