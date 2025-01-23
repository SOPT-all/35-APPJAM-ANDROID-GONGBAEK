package com.sopt.gongbaek.data.mapper.todata

import com.sopt.gongbaek.data.remote.dto.request.RegisterTimeTableRequestDto
import com.sopt.gongbaek.domain.model.TimeTable

fun TimeTable.toData() = RegisterTimeTableRequestDto(
    weekDay = this.weekDay,
    startTime = this.startTime,
    endTime = this.endTime
)
