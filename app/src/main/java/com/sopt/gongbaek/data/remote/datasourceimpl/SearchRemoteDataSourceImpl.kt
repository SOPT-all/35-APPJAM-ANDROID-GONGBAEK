package com.sopt.gongbaek.data.remote.datasourceimpl

import com.sopt.gongbaek.data.remote.datasource.SearchRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.response.SearchMajorsResponseDto
import com.sopt.gongbaek.data.remote.dto.response.SearchUniversitiesResponseDto
import com.sopt.gongbaek.data.remote.service.SearchService
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRemoteDataSource {

    override suspend fun getSearchUniversitiesResult(universityName: String): ApiResponse<SearchUniversitiesResponseDto> =
        searchService.getSearchUniversitiesResult(universityName)

    override suspend fun getSearchMajorsResult(universityName: String, majorName: String): ApiResponse<SearchMajorsResponseDto> =
        searchService.getSearchMajorsResult(universityName, majorName)
}
