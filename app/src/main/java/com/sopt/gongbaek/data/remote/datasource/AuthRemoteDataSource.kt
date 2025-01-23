package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.base.NullableApiResponse
import com.sopt.gongbaek.data.remote.dto.request.RegisterUserInfoRequestDto
import com.sopt.gongbaek.data.remote.dto.response.RegisterUserInfoResponseDto
import com.sopt.gongbaek.data.remote.dto.response.UserLectureTimeTableResponseDto
import com.sopt.gongbaek.data.remote.dto.response.UserProfileResponseDto

interface AuthRemoteDataSource {
    suspend fun registerUserInfo(registerUserInfoRequestDto: RegisterUserInfoRequestDto): ApiResponse<RegisterUserInfoResponseDto>
    suspend fun validateNickname(nickname: String): NullableApiResponse<Unit>
    suspend fun getUserProfile(): ApiResponse<UserProfileResponseDto>
    suspend fun getUserLectureTimeTable(): ApiResponse<List<UserLectureTimeTableResponseDto>>
}
