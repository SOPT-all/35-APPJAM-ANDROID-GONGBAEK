package com.sopt.gongbaek.domain.repository

import com.sopt.gongbaek.data.remote.dto.response.GroupRegisterResponseDto
import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.model.GroupRegisterInfo

interface GroupRepository {
    suspend fun getMyGroups(category: String, status: Boolean): Result<List<GroupInfo>>
    suspend fun getGroups(category: String?): Result<List<GroupInfo>>
    suspend fun postGroup(groupRegisterInfo: GroupRegisterInfo): Result<GroupRegisterResponseDto>
}
