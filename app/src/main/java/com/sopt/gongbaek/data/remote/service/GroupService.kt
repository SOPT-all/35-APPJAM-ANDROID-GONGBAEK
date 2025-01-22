package com.sopt.gongbaek.data.remote.service

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.request.MyGroupsRequestDto
import com.sopt.gongbaek.data.remote.dto.response.MyGroupsResponseDto
import retrofit2.http.Body
import retrofit2.http.GET

interface GroupService {
    @GET("/api/v1/my/groups")
    suspend fun getMyGroups(
        @Body myGroupsRequestDto: MyGroupsRequestDto
    ): ApiResponse<MyGroupsResponseDto>
}
