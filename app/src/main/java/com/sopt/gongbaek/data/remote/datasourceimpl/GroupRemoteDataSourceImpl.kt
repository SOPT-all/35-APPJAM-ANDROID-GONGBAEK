package com.sopt.gongbaek.data.remote.datasourceimpl

import com.sopt.gongbaek.data.remote.datasource.GroupRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto
import com.sopt.gongbaek.data.remote.service.GroupService
import javax.inject.Inject

class GroupRemoteDataSourceImpl @Inject constructor(
    private val groupService: GroupService
) : GroupRemoteDataSource {
    override suspend fun getMyGroups(category: String, status: Boolean): ApiResponse<MyGroupsResponseDto> =
        groupService.getMyGroups(category = category, status = status)
}
