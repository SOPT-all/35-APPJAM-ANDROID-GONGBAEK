package com.sopt.gongbaek.data.remote.dto.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NullableApiResponse<T>(
    @SerialName("success") val success: Boolean,
    @SerialName("code") val code: Int,
    @SerialName("message") val message: String,
    @SerialName("data") val data: T?
)
