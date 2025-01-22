package com.sopt.gongbaek.data.remote.service

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.response.GroupCommentsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {
    @GET("/api/v1/comments")
    suspend fun getGroupComments(
        @Query("groupId") groupId: Int,
        @Query("groupType") groupType: String
    ): ApiResponse<GroupCommentsResponseDto>
}