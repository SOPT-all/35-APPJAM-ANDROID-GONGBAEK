package com.sopt.gongbaek.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyGroupsResponseDto(
    @SerialName("groups") val groups: List<BaseGroupInfoResponseDto>
)
