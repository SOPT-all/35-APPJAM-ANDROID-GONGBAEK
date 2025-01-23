package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.mapper.todomain.toDomain
import com.sopt.gongbaek.data.remote.datasource.GroupRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.request.MyGroupsRequestDto
import com.sopt.gongbaek.data.remote.util.handleApiResponse
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.model.NearestGroup
import com.sopt.gongbaek.domain.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupDataSource: GroupRemoteDataSource
) : GroupRepository {
    override suspend fun getMyGroups(category: String, status: Boolean): Result<List<GroupInfo>> {
        return runCatching {
            groupDataSource.getMyGroups(
                myGroupsRequestDto = MyGroupsRequestDto(
                    category = category,
                    status = status
                )
            ).handleApiResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun getGroups(category: String?): Result<List<GroupInfo>> {
        return runCatching {
            groupDataSource.getGroups(
                category = category
            ).handleApiResponse().getOrThrow().map { group -> group.toDomain() }
        }
    }

    override suspend fun getNearestGroup(): Result<NearestGroup> =
        runCatching {
            groupDataSource.getNearestGroup().handleApiResponse().getOrThrow().toDomain()
        }
}
