package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.RegisterUserInfoResponseDto
import com.sopt.gongbaek.domain.model.UserAuth

fun RegisterUserInfoResponseDto.toDomain() = UserAuth(
    userId = this.userId,
    accessToken = this.accessToken,
    refreshToken = this.refreshToken
)
