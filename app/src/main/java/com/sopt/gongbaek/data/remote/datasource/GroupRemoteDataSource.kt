package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.response.GroupDetailResponseDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto

interface GroupRemoteDataSource {
    suspend fun getMyGroups(category: String, status: Boolean): ApiResponse<MyGroupsResponseDto>
    suspend fun getGroupDetail(groupId: Int, groupType: String): ApiResponse<GroupDetailResponseDto>
}
