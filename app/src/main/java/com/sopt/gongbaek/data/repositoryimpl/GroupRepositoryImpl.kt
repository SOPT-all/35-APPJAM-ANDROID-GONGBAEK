package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.mapper.todata.toData
import com.sopt.gongbaek.data.mapper.todomain.toDomain
import com.sopt.gongbaek.data.remote.datasource.GroupRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.request.MyGroupsRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupRegisterResponseDto
import com.sopt.gongbaek.data.remote.util.handleApiResponse
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.model.GroupRegisterInfo
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

    override suspend fun postGroup(groupRegisterInfo: GroupRegisterInfo): Result<GroupRegisterResponseDto> {
        return runCatching {
            groupDataSource.postGroup(
                groupRegisterRequestDto = groupRegisterInfo.toData()
            ).handleApiResponse().getOrThrow()
        }
    }
}
