package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.request.MyGroupsRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupListGroupResponseDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto
import com.sopt.gongbaek.data.remote.dto.response.NearestGroupResponseDto

interface GroupRemoteDataSource {
    suspend fun getMyGroups(myGroupsRequestDto: MyGroupsRequestDto): ApiResponse<MyGroupsResponseDto>

    suspend fun getGroups(category: String?): ApiResponse<List<GroupListGroupResponseDto>>

    suspend fun getNearestGroup(): ApiResponse<NearestGroupResponseDto>
}
