package com.sopt.gongbaek.data.remote.service

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.base.NullableApiResponse
import com.sopt.gongbaek.data.remote.dto.request.PostCommentRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupCommentsResponseDto
import com.sopt.gongbaek.data.remote.dto.response.PostCommentResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentService {
    @GET("/api/v1/comments")
    suspend fun getGroupComments(
        @Query("groupId") groupId: Int,
        @Query("groupType") groupType: String
    ): ApiResponse<GroupCommentsResponseDto>

    @POST("/api/v1/comment")
    suspend fun postComment(
        @Body postCommentRequestDto: PostCommentRequestDto
    ): NullableApiResponse<PostCommentResponseDto>
}