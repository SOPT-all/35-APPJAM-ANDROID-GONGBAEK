package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.UserTimeTableResponseDto
import com.sopt.gongbaek.domain.model.UserLectureTimeTable

fun UserTimeTableResponseDto.toDomain(): List<UserLectureTimeTable> =
    timeTable.map { lectureTimeTable ->
        UserLectureTimeTable(
            idx = lectureTimeTable.idx,
            weekDay = lectureTimeTable.weekDay,
            startTime = lectureTimeTable.startTime,
            endTime = lectureTimeTable.endTime
        )
    }
