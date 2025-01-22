package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.request.MyGroupsRequestDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto

interface GroupRemoteDataSource {
    suspend fun getMyGroups(myGroupsRequestDto: MyGroupsRequestDto): ApiResponse<MyGroupsResponseDto>
}
