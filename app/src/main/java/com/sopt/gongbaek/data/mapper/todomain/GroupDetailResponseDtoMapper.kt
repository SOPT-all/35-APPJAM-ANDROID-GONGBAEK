package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.GroupDetailResponseDto
import com.sopt.gongbaek.domain.model.GroupInfo

fun GroupDetailResponseDto.toDomain() = GroupInfo(
    groupId = groupId,
    coverImg = coverImg,
    status = status,
    category = category,
    cycle = groupType,
    title = groupTitle,
    date = weekDate,
    dayOfWeek = weekDay,
    startTime = startTime,
    endTime = endTime,
    place = location,
    introduction = introduction,
    maxPeopleCount = maxPeopleCount,
    currentPeopleCount = currentPeopleCount,
    isHost = isHost,
    isApply = isApply
)