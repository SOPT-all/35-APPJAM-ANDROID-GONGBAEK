package com.sopt.gongbaek.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyGroupsRequestDto(
    @SerialName("category") val category: String,
    @SerialName("status") val status: Boolean
)
