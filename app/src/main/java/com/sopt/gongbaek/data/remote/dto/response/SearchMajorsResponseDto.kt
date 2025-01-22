package com.sopt.gongbaek.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMajorsResponseDto(
    @SerialName("schoolMajors")
    val majors: List<String>
)
