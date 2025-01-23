package com.sopt.gongbaek.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApplyGroupRequestDto(
    @SerialName("groupId") val groupId: Int,
    @SerialName("groupType") val groupType: String
)
