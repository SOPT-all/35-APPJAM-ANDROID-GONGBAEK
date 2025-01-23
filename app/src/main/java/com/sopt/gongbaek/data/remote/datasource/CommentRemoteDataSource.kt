package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.base.NullableApiResponse
import com.sopt.gongbaek.data.remote.dto.request.PostCommentRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupCommentsResponseDto
import com.sopt.gongbaek.data.remote.dto.response.PostCommentResponseDto

interface CommentRemoteDataSource {
    suspend fun getGroupComments(isPublic: Boolean, groupId: Int, groupType: String): ApiResponse<GroupCommentsResponseDto>
    suspend fun postComment(postCommentRequestDto: PostCommentRequestDto): NullableApiResponse<PostCommentResponseDto>
}
