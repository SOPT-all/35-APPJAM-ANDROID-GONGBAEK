package com.sopt.gongbaek.data.remote.service

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.response.GroupListResponseDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto
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
    ): ApiResponse<List<GroupListResponseDto>>
}
