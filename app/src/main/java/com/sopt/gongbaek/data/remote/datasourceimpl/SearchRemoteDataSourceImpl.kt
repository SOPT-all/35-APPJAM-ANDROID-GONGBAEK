package com.sopt.gongbaek.data.remote.datasourceimpl

import com.sopt.gongbaek.data.remote.datasource.SearchRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.response.SearchUniversitiesResponseDto
import com.sopt.gongbaek.data.remote.service.SearchService
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRemoteDataSource {

    override suspend fun getSearchUniversitiesResult(universityName: String): ApiResponse<SearchUniversitiesResponseDto> =
        searchService.getSearchUniversitiesResult(universityName)


}
