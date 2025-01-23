package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.request.GroupRegisterRequestDto
import com.sopt.gongbaek.data.remote.dto.request.MyGroupsRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupListGroupResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupRegisterResponseDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto

interface GroupRemoteDataSource {
    suspend fun getMyGroups(myGroupsRequestDto: MyGroupsRequestDto): ApiResponse<MyGroupsResponseDto>
    suspend fun getGroups(category: String?): ApiResponse<List<GroupListGroupResponseDto>>
    suspend fun postGroup(groupRegisterRequestDto: GroupRegisterRequestDto): ApiResponse<GroupRegisterResponseDto>
}
