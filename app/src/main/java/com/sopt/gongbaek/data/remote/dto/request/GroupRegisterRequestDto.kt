package com.sopt.gongbaek.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupRegisterRequestDto(
    @SerialName("groupType") val groupType: String,
    @SerialName("weekDate") val weekDate: String?,
    @SerialName("weekDay") val weekDay: String,
    @SerialName("startTime") val startTime: Double,
    @SerialName("endTime") val endTime: Double,
    @SerialName("category") val category: String,
    @SerialName("coverImg") val coverImg: Int,
    @SerialName("location") val location: String,
    @SerialName("maxPeopleCount") val maxPeopleCount: Int,
    @SerialName("groupTitle") val groupTitle: String,
    @SerialName("introduction") val introduction: String
)
