package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.base.NullableApiResponse
import com.sopt.gongbaek.data.remote.dto.request.ApplyGroupRequestDto
import com.sopt.gongbaek.data.remote.dto.request.GroupRegisterRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupDetailResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupHostResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupListGroupResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupMembersResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupRegisterResponseDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto

interface GroupRemoteDataSource {
    suspend fun getMyGroups(category: String, status: Boolean): ApiResponse<MyGroupsResponseDto>
    suspend fun getGroupDetail(groupId: Int, groupType: String): ApiResponse<GroupDetailResponseDto>
    suspend fun getGroupHost(groupId: Int, groupType: String): ApiResponse<GroupHostResponseDto>
    suspend fun applyGroup(applyGroupRequestDto: ApplyGroupRequestDto): NullableApiResponse<Unit>
    suspend fun getGroups(category: String?): ApiResponse<List<GroupListGroupResponseDto>>
    suspend fun postGroup(groupRegisterRequestDto: GroupRegisterRequestDto): ApiResponse<GroupRegisterResponseDto>
    suspend fun getGroupMembers(groupId: Int, groupType: String): ApiResponse<GroupMembersResponseDto>
}
