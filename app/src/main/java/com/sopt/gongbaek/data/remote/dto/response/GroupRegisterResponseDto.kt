package com.sopt.gongbaek.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupRegisterResponseDto(
    @SerialName("groupId") val groupId: Int
)