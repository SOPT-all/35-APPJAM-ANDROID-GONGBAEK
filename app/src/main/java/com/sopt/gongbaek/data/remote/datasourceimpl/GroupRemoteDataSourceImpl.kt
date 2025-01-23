package com.sopt.gongbaek.data.remote.datasourceimpl

import com.sopt.gongbaek.data.remote.datasource.GroupRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.base.NullableApiResponse
import com.sopt.gongbaek.data.remote.dto.request.GroupRegisterRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupDetailResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupHostResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupListGroupResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupMembersResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupRegisterResponseDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto
import com.sopt.gongbaek.data.remote.service.GroupService
import javax.inject.Inject

class GroupRemoteDataSourceImpl @Inject constructor(
    private val groupService: GroupService
) : GroupRemoteDataSource {
    override suspend fun getMyGroups(category: String, status: Boolean): ApiResponse<MyGroupsResponseDto> =
        groupService.getMyGroups(category = category, status = status)

    override suspend fun getGroupDetail(groupId: Int, groupType: String): ApiResponse<GroupDetailResponseDto> =
        groupService.getGroupDetail(groupId = groupId, groupType = groupType)

    override suspend fun getGroupHost(groupId: Int, groupType: String): ApiResponse<GroupHostResponseDto> =
        groupService.getGroupHost(groupId = groupId, groupType = groupType)

    override suspend fun applyGroup(groupId: Int, groupType: String): NullableApiResponse<Unit> =
        groupService.applyGroup(groupId = groupId, groupType = groupType)

    override suspend fun getGroups(category: String?): ApiResponse<List<GroupListGroupResponseDto>> =
        groupService.getGroups(category = category)

    override suspend fun postGroup(groupRegisterRequestDto: GroupRegisterRequestDto): ApiResponse<GroupRegisterResponseDto> =
        groupService.postGroup(groupRegisterRequestDto = groupRegisterRequestDto)

    override suspend fun getGroupMembers(groupId: Int, groupType: String): ApiResponse<GroupMembersResponseDto> =
        groupService.getGroupMembers(groupId = groupId, groupType = groupType)
}
