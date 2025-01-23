package com.sopt.gongbaek.data.remote.service

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.base.NullableApiResponse
import com.sopt.gongbaek.data.remote.dto.response.GroupDetailResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupHostResponseDto
import com.sopt.gongbaek.data.remote.dto.request.GroupRegisterRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupListGroupResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupMembersResponseDto
import com.sopt.gongbaek.data.remote.dto.response.GroupRegisterResponseDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GroupService {
    @GET("/api/v1/my/groups")
    suspend fun getMyGroups(
        @Query("category") category: String,
        @Query("status") status: Boolean
    ): ApiResponse<MyGroupsResponseDto>

    @GET("/api/v1/fill/info")
    suspend fun getGroupDetail(
        @Query("groupId") groupId: Int,
        @Query("groupType") groupType: String
    ): ApiResponse<GroupDetailResponseDto>

    @GET("/api/v1/fill/user/info")
    suspend fun getGroupHost(
        @Query("groupId") groupId: Int,
        @Query("groupType") groupType: String
    ): ApiResponse<GroupHostResponseDto>

    @POST("/api/v1/group")
    suspend fun applyGroup(
        @Query("groupId") groupId: Int,
        @Query("groupType") groupType: String
    ): NullableApiResponse<Unit>

    @GET("/api/v1/fill/groups")
    suspend fun getGroups(
        @Query("category") category: String? = null
    ): ApiResponse<List<GroupListGroupResponseDto>>

    @POST("/api/v1/gongbaek")
    suspend fun postGroup(
        @Body groupRegisterRequestDto: GroupRegisterRequestDto
    ): ApiResponse<GroupRegisterResponseDto>

    @GET("/api/v1/fill/members")
    suspend fun getGroupMembers(
        @Query("groupId") groupId: Int,
        @Query("groupType") groupType: String
    ): ApiResponse<GroupMembersResponseDto>
}
