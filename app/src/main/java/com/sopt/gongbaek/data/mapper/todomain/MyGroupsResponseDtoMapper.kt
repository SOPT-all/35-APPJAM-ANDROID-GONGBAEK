package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto
import com.sopt.gongbaek.domain.model.GroupInfo

fun MyGroupsResponseDto.toDomain(): List<GroupInfo> {
    return groups.map { group ->
        GroupInfo(
            groupId = group.groupId,
            coverImg = group.coverImg,
            status = group.status,
            category = group.category,
            cycle = group.groupType,
            title = group.groupTitle,
            date = group.weekDate,
            dayOfWeek = group.weekDay,
            startTime = group.startTime,
            endTime = group.endTime,
            place = group.location
        )
    }
}
