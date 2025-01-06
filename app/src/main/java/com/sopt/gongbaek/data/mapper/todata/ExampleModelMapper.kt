package com.sopt.gongbaek.data.mapper.todata

import com.sopt.gongbaek.data.remote.dto.request.ExampleRequestDto
import com.sopt.gongbaek.domain.model.ExampleModel

fun ExampleModel.toData(): ExampleRequestDto = ExampleRequestDto(
    id = this.id,
    name = this.name
)