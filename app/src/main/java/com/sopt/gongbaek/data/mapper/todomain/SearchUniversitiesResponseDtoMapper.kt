package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.SearchUniversitiesResponseDto
import com.sopt.gongbaek.domain.model.Universities

fun SearchUniversitiesResponseDto.toDomain() =
    Universities(
        universities = this.universities
    )
