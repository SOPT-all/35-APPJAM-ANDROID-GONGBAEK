package com.sopt.gongbaek.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendGroupInfoResponseDto(
    @SerialName("groupId")
    val groupId: Int,
    @SerialName("category")
    val category: String,
    @SerialName("coverImg")
    val coverImg: Int,
    @SerialName("profileImg")
    val profileImg: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("groupType")
    val groupType: String,
    @SerialName("groupTitle")
    val groupTitle: String,
    @SerialName("weekDay")
    val weekDay: String,
    @SerialName("weekDate")
    val weekDate: String?,
    @SerialName("startTime")
    val startTime: Double,
    @SerialName("endTime")
    val endTime: Double,
    @SerialName("location")
    val location: String
)
