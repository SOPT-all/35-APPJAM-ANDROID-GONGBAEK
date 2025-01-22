package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.request.RegisterUserInfoRequestDto
import com.sopt.gongbaek.data.remote.dto.response.RegisterUserInfoResponseDto

interface AuthRemoteDataSource {
    suspend fun registerUserInfo(registerUserInfoRequestDto: RegisterUserInfoRequestDto): ApiResponse<RegisterUserInfoResponseDto>
}
