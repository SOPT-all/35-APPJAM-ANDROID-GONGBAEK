package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.response.SearchMajorsResponseDto
import com.sopt.gongbaek.data.remote.dto.response.SearchUniversitiesResponseDto

interface SearchRemoteDataSource {
    suspend fun getSearchUniversitiesResult(universityName: String): ApiResponse<SearchUniversitiesResponseDto>
    suspend fun getSearchMajorsResult(universityName: String, majorName: String): ApiResponse<SearchMajorsResponseDto>
}
