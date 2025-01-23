package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.mapper.todata.toData
import com.sopt.gongbaek.data.mapper.todomain.toDomain
import com.sopt.gongbaek.data.remote.datasource.GroupRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.request.ApplyGroupRequestDto
import com.sopt.gongbaek.data.remote.dto.response.GroupRegisterResponseDto
import com.sopt.gongbaek.data.remote.util.handleApiResponse
import com.sopt.gongbaek.data.remote.util.handleNullableApiResponse
import com.sopt.gongbaek.domain.model.GroupHost
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.model.GroupMembers
import com.sopt.gongbaek.domain.model.GroupRegisterInfo
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

    override suspend fun getGroupHost(groupId: Int, groupType: String): Result<GroupHost> =
        runCatching {
            groupDataSource.getGroupHost(groupId = groupId, groupType = groupType).handleApiResponse().getOrThrow().toDomain()
        }

    override suspend fun applyGroup(groupId: Int, groupType: String): Result<Unit> =
        runCatching {
            groupDataSource.applyGroup(applyGroupRequestDto = ApplyGroupRequestDto(groupId = groupId, groupType = groupType)).handleNullableApiResponse().getOrThrow()
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

    override suspend fun getGroupMembers(groupId: Int, groupType: String): Result<GroupMembers> =
        runCatching {
            groupDataSource.getGroupMembers(groupId = groupId, groupType = groupType).handleApiResponse().getOrThrow().toDomain()
        }
}
