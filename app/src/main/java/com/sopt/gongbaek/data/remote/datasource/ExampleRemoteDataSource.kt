package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.request.ExampleRequestDto
import com.sopt.gongbaek.data.remote.dto.response.ExampleResponseDto

interface ExampleRemoteDataSource {
    suspend fun getExampleData(): ApiResponse<ExampleResponseDto>
    suspend fun postExampleData(exampleRequestDto: ExampleRequestDto): ApiResponse<Unit>
}