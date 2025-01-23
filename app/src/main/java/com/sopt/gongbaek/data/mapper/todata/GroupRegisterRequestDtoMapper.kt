package com.sopt.gongbaek.data.mapper.todata

import com.sopt.gongbaek.data.remote.dto.request.GroupRegisterRequestDto
import com.sopt.gongbaek.domain.model.GroupRegisterInfo

fun GroupRegisterInfo.toData(): GroupRegisterRequestDto =
    GroupRegisterRequestDto(
        groupType = groupType,
        weekDate = weekDate,
        weekDay = weekDay,
        startTime = startTime,
        endTime = endTime,
        category = category,
        coverImg = coverImg,
        location = location,
        maxPeopleCount = maxPeopleCount,
        groupTitle = groupTitle,
        introduction = introduction
    )
