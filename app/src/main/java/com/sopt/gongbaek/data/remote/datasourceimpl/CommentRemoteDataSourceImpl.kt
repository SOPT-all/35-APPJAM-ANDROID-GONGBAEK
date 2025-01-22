package com.sopt.gongbaek.data.remote.datasourceimpl

import com.sopt.gongbaek.data.remote.datasource.CommentRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.response.GroupCommentsResponseDto
import com.sopt.gongbaek.data.remote.service.CommentService
import javax.inject.Inject

class CommentRemoteDataSourceImpl @Inject constructor(
    private val commentService: CommentService
): CommentRemoteDataSource {
    override suspend fun getGroupComments(groupId: Int, groupType: String): ApiResponse<GroupCommentsResponseDto> =
        commentService.getGroupComments(groupId = groupId, groupType = groupType)
}