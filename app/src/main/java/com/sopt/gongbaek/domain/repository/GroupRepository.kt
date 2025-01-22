package com.sopt.gongbaek.domain.repository

import com.sopt.gongbaek.domain.model.GroupInfo

interface GroupRepository {
    suspend fun getMyGroups(category: String, status: Boolean): Result<List<GroupInfo>>
    suspend fun getGroupDetail(groupId: Int, groupType: String): Result<GroupInfo>
}
