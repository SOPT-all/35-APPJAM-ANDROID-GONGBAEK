package com.sopt.gongbaek.data.remote.datasource

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.response.GroupCommentsResponseDto

interface CommentRemoteDataSource {
    suspend fun getGroupComments(groupId: Int, groupType: String): ApiResponse<GroupCommentsResponseDto>
}