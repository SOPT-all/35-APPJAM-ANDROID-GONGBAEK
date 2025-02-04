package com.sopt.gongbaek.domain.repository

import com.sopt.gongbaek.data.remote.dto.response.GroupRegisterResponseDto
import com.sopt.gongbaek.domain.model.GroupHost
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.model.NearestGroup
import com.sopt.gongbaek.domain.model.RecommendGroupInfo
import com.sopt.gongbaek.domain.model.GroupMembers
import com.sopt.gongbaek.domain.model.GroupRegisterInfo

interface GroupRepository {
    suspend fun getMyGroups(category: String, status: Boolean): Result<List<GroupInfo>>
    suspend fun getGroupDetail(groupId: Int, groupType: String): Result<GroupInfo>
    suspend fun getGroupHost(groupId: Int, groupType: String): Result<GroupHost>
    suspend fun applyGroup(groupId: Int, groupType: String): Result<Unit>
    suspend fun getGroups(category: String?): Result<List<GroupInfo>>
    suspend fun getNearestGroup(): Result<NearestGroup>
    suspend fun getLatestGroup(groupType: String): Result<List<RecommendGroupInfo>>
    suspend fun postGroup(groupRegisterInfo: GroupRegisterInfo): Result<GroupRegisterResponseDto>
    suspend fun getGroupMembers(groupId: Int, groupType: String): Result<GroupMembers>
}
