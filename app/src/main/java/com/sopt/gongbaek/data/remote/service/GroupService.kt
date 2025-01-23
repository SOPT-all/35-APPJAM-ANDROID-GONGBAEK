package com.sopt.gongbaek.data.remote.service

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.response.GroupListGroupResponseDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto
import com.sopt.gongbaek.data.remote.dto.response.NearestGroupResponseDto
import com.sopt.gongbaek.data.remote.dto.response.RecommendGroupInfoResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GroupService {
    @GET("/api/v1/my/groups")
    suspend fun getMyGroups(
        @Query("category") category: String,
        @Query("status") status: Boolean
    ): ApiResponse<MyGroupsResponseDto>

    @GET("/api/v1/fill/groups")
    suspend fun getGroups(
        @Query("category") category: String? = null
    ): ApiResponse<List<GroupListGroupResponseDto>>

    @GET("/api/v1/group/my/participation")
    suspend fun getNearestGroup(): ApiResponse<NearestGroupResponseDto>

    @GET("/api/v1/group/latest")
    suspend fun getLatestGroup(
        @Query("groupType") groupType: String
    ): ApiResponse<List<RecommendGroupInfoResponseDto>>
}
