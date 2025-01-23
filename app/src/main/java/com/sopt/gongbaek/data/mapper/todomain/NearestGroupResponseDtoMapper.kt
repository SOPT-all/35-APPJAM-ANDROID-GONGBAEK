package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.NearestGroupResponseDto
import com.sopt.gongbaek.domain.model.NearestGroup

fun NearestGroupResponseDto.toDomain() = NearestGroup(
    groupId = groupId,
    category = category,
    groupType = groupType,
    groupTitle = groupTitle,
    weekDay = weekDay ?: "",
    weekDate = weekDate ?: "",
    currentPeopleCount = currentPeopleCount,
    maxPeopleCount = maxPeopleCount,
    startTime = startTime,
    endTime = endTime,
    location = location
)
