package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.mapper.todomain.toDomain
import com.sopt.gongbaek.data.remote.datasource.GroupRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.request.MyGroupsRequestDto
import com.sopt.gongbaek.data.remote.util.handleApiResponse
import com.sopt.gongbaek.domain.model.GroupInfo
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
}
