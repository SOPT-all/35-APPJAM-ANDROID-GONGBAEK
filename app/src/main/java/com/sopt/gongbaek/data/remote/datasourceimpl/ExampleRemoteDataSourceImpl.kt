package com.sopt.gongbaek.data.remote.datasourceimpl

import com.sopt.gongbaek.data.remote.datasource.ExampleRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.request.ExampleRequestDto
import com.sopt.gongbaek.data.remote.dto.response.ExampleResponseDto
import com.sopt.gongbaek.data.remote.service.ExampleService
import javax.inject.Inject

class ExampleRemoteDataSourceImpl @Inject constructor(
    private val exampleService: ExampleService
) : ExampleRemoteDataSource {
    override suspend fun getExampleData(): ApiResponse<ExampleResponseDto> =
        exampleService.getExampleData()


    override suspend fun postExampleData(exampleRequestDto: ExampleRequestDto): ApiResponse<Unit> =
        exampleService.postExampleData(exampleRequestDto = exampleRequestDto)
}