package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.SearchMajorsResponseDto
import com.sopt.gongbaek.domain.model.Majors

fun SearchMajorsResponseDto.toDomain() =
    Majors(
        majors = this.majors
    )
