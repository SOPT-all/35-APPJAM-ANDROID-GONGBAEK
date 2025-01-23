package com.sopt.gongbaek.data.remote.service

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.request.GroupRegisterRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupListGroupResponseDto
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

    @GET("/api/v1/fill/groups")
    suspend fun getGroups(
        @Query("category") category: String? = null
    ): ApiResponse<List<GroupListGroupResponseDto>>

    @POST("/api/v1/gongbaek")
    suspend fun postGroup(
        @Body groupRegisterRequestDto: GroupRegisterRequestDto
    ): ApiResponse<GroupRegisterResponseDto>
}
