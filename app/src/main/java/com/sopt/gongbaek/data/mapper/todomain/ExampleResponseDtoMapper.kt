package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.ExampleResponseDto
import com.sopt.gongbaek.domain.model.ExampleModel

fun ExampleResponseDto.toDomain(): ExampleModel = ExampleModel(
    id = this.id,
    name = this.name
)