package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.UserProfileResponseDto
import com.sopt.gongbaek.domain.model.UserProfile

fun UserProfileResponseDto.toDomain(): UserProfile =
    UserProfile(
        nickname = nickname,
        schoolName = schoolName
    )
