package com.sopt.gongbaek.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupHostResponseDto(
    @SerialName("profileImg") val profileImg: Int,
    @SerialName("nickname") val nickname: String,
    @SerialName("sex") val sex: String,
    @SerialName("schoolMajor") val schoolMajor: String,
    @SerialName("enterYear") val enterYear: Int,
    @SerialName("schoolGrade") val schoolGrade: Int,
    @SerialName("mbti") val mbti: String,
    @SerialName("introduction") val introduction: String
)