package com.sopt.gongbaek.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupCommentsResponseDto(
    @SerialName("groupId") val groupId: Int,
    @SerialName("groupStatus") val groupStatus: String,
    @SerialName("groupCycle") val groupCycle: String,
    @SerialName("commentCount") val commentCount: Int,
    @SerialName("comments") val comments: List<GroupCommentResponseDto>
) {
    @Serializable
    data class GroupCommentResponseDto(
        @SerialName("commentId") val commentId: Int,
        @SerialName("nickname") val nickname: String,
        @SerialName("body") val body: String,
        @SerialName("createdAt") val createdAt: String,
        @SerialName("isGroupHost") val isGroupHost: Boolean,
        @SerialName("isWriter") val isWriter: Boolean
    )
}