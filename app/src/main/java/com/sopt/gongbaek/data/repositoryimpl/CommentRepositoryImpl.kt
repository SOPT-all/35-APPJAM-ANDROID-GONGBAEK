package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.mapper.todomain.toDomain
import com.sopt.gongbaek.data.remote.datasource.CommentRemoteDataSource
import com.sopt.gongbaek.data.remote.util.handleApiResponse
import com.sopt.gongbaek.domain.model.GroupComments
import com.sopt.gongbaek.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentRemoteDataSource: CommentRemoteDataSource
) : CommentRepository {
    override suspend fun getGroupComments(groupId: Int, groupType: String): Result<GroupComments> =
        runCatching {
            commentRemoteDataSource.getGroupComments(groupId = groupId, groupType = groupType).handleApiResponse().getOrThrow().toDomain()
        }
}