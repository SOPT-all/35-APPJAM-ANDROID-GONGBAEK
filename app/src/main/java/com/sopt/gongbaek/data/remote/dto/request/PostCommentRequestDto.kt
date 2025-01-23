package com.sopt.gongbaek.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostCommentRequestDto(
    @SerialName("groupId") val groupId: Int,
    @SerialName("groupType") val groupType: String,
    @SerialName("isPublic") val isPublic: Boolean,
    @SerialName("body") val body: String
)
