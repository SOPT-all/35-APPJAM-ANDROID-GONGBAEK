package com.sopt.gongbaek.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupMembersResponseDto(
    @SerialName("maxPeopleCount") val maxPeopleCount: Int,
    @SerialName("currentPeopleCount") val currentPeopleCount: Int,
    @SerialName("members") val members: List<GroupMemberResponseDto>
) {
    @Serializable
    data class GroupMemberResponseDto(
        @SerialName("profileImg") val profileImg: Int,
        @SerialName("nickname") val nickname: String,
        @SerialName("isHost") val isHost: Boolean
    )
}
