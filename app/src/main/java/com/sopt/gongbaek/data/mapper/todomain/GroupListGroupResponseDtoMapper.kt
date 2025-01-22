package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.GroupListGroupResponseDto
import com.sopt.gongbaek.domain.model.GroupInfo

fun GroupListGroupResponseDto.toDomain(): GroupInfo =
    GroupInfo(
        groupId = groupId,
        coverImg = coverImg,
        status = "RECRUITING",
        category = category,
        cycle = groupType,
        title = groupTitle,
        date = weekDate,
        dayOfWeek = weekDay,
        startTime = startTime,
        endTime = endTime,
        place = location
    )
