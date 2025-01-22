package com.sopt.gongbaek.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseGroupInfoResponseDto(
    @SerialName("groupId") val groupId: Int,
    @SerialName("coverImg") val coverImg: Int,
    @SerialName("status") val status: String,
    @SerialName("category") val category: String,
    @SerialName("groupType") val groupType: String,
    @SerialName("groupTitle") val groupTitle: String,
    @SerialName("weekDate") val weekDate: String?,
    @SerialName("weekDay") val weekDay: String,
    @SerialName("startTime") val startTime: Double,
    @SerialName("endTime") val endTime: Double,
    @SerialName("location") val location: String
)