package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.GroupListResponseDto
import com.sopt.gongbaek.domain.model.GroupInfo

fun List<GroupListResponseDto>.toDomain(): List<GroupInfo> {
    return map { dto ->
        GroupInfo(
            groupId = dto.groupId,
            coverImg = dto.coverImg,
            status = "RECRUITING",
            category = dto.category,
            cycle = dto.groupType,
            title = dto.groupTitle,
            date = dto.weekDate,
            dayOfWeek = dto.weekDay,
            startTime = dto.startTime,
            endTime = dto.endTime,
            place = dto.location
        )
    }
}
