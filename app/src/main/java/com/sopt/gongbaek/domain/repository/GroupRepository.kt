package com.sopt.gongbaek.domain.repository

import com.sopt.gongbaek.domain.model.GroupInfo

interface GroupRepository {
    suspend fun getMyGroups(category: String, status: Boolean): Result<List<GroupInfo>>
}
