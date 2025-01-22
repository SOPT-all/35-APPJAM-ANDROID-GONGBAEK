package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.mapper.todomain.toDomain
import com.sopt.gongbaek.data.remote.datasource.GroupRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.response.GroupDetailResponseDto
import com.sopt.gongbaek.data.remote.util.handleApiResponse
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupDataSource: GroupRemoteDataSource
) : GroupRepository {
    override suspend fun getMyGroups(category: String, status: Boolean): Result<List<GroupInfo>> =
        runCatching {
            groupDataSource.getMyGroups(category = category, status = status).handleApiResponse().getOrThrow().toDomain()
        }

    override suspend fun getGroupDetail(groupId: Int, groupType: String): Result<GroupInfo> =
        runCatching {
            groupDataSource.getGroupDetail(groupId = groupId, groupType = groupType).handleApiResponse().getOrThrow().toDomain()
        }
}

