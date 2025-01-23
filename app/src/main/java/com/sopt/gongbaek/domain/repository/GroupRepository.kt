package com.sopt.gongbaek.domain.repository

import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.model.NearestGroup
import com.sopt.gongbaek.domain.model.RecommendGroupInfo

interface GroupRepository {
    suspend fun getMyGroups(category: String, status: Boolean): Result<List<GroupInfo>>
    suspend fun getGroups(category: String?): Result<List<GroupInfo>>
    suspend fun getNearestGroup(): Result<NearestGroup>
    suspend fun getLatestGroup(groupType: String): Result<List<RecommendGroupInfo>>
}
