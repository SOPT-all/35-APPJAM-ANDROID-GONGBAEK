package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.UserLectureTimeTableResponseDto
import com.sopt.gongbaek.domain.model.UserLectureTimeTable

fun UserLectureTimeTableResponseDto.toDomain(): UserLectureTimeTable =
    UserLectureTimeTable(
        weekDay = weekDay,
        startTime = startTime,
        endTime = endTime
    )
