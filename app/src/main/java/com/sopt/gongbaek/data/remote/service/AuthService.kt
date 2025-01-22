package com.sopt.gongbaek.data.remote.service

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.request.RegisterUserInfoRequestDto
import com.sopt.gongbaek.data.remote.dto.response.RegisterUserInfoResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/api/v1/user/signup")
    suspend fun requestUserInfo(
        @Body registerUserInfoRequestDto: RegisterUserInfoRequestDto
    ): ApiResponse<RegisterUserInfoResponseDto>
}
