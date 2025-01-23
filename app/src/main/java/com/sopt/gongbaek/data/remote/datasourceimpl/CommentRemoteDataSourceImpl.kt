package com.sopt.gongbaek.data.remote.datasourceimpl

import com.sopt.gongbaek.data.remote.datasource.CommentRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.base.NullableApiResponse
import com.sopt.gongbaek.data.remote.dto.request.PostCommentRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupCommentsResponseDto
import com.sopt.gongbaek.data.remote.dto.response.PostCommentResponseDto
import com.sopt.gongbaek.data.remote.service.CommentService
import javax.inject.Inject

class CommentRemoteDataSourceImpl @Inject constructor(
    private val commentService: CommentService
) : CommentRemoteDataSource {
    override suspend fun getGroupComments(isPublic: Boolean, groupId: Int, groupType: String): ApiResponse<GroupCommentsResponseDto> =
        commentService.getGroupComments(isPublic = isPublic, groupId = groupId, groupType = groupType)

    override suspend fun postComment(postCommentRequestDto: PostCommentRequestDto): NullableApiResponse<PostCommentResponseDto> =
        commentService.postComment(postCommentRequestDto = postCommentRequestDto)
}
