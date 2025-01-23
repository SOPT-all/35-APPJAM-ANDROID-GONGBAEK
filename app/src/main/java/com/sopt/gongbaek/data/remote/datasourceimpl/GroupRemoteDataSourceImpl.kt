package com.sopt.gongbaek.data.remote.datasourceimpl

import com.sopt.gongbaek.data.remote.datasource.GroupRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.request.MyGroupsRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupListGroupResponseDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto
import com.sopt.gongbaek.data.remote.dto.response.NearestGroupResponseDto
import com.sopt.gongbaek.data.remote.service.GroupService
import javax.inject.Inject

class GroupRemoteDataSourceImpl @Inject constructor(
    private val groupService: GroupService
) : GroupRemoteDataSource {
    override suspend fun getMyGroups(myGroupsRequestDto: MyGroupsRequestDto): ApiResponse<MyGroupsResponseDto> =
        groupService.getMyGroups(category = myGroupsRequestDto.category, status = myGroupsRequestDto.status)

    override suspend fun getGroups(category: String?): ApiResponse<List<GroupListGroupResponseDto>> =
        groupService.getGroups(category = category)

    override suspend fun getNearestGroup(): ApiResponse<NearestGroupResponseDto> =
        groupService.getNearestGroup()
}
