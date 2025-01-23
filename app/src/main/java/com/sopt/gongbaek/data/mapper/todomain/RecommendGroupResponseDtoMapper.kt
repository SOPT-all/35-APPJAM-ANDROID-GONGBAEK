package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.RecommendGroupInfoResponseDto
import com.sopt.gongbaek.domain.model.RecommendGroupInfo

fun RecommendGroupInfoResponseDto.toDomain() =
    RecommendGroupInfo(
        groupId = groupId,
        category = category,
        coverImg = coverImg,
        profileImg = profileImg,
        nickname = nickname,
        groupType = groupType,
        groupTitle = groupTitle,
        weekDay = weekDay ?: "",
        weekDate = weekDate ?: "",
        startTime = startTime,
        endTime = endTime,
        location = location
    )
