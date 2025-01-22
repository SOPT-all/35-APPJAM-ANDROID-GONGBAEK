package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.repository.GroupRepository

class GetMyGroupsUseCase(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(category: String, status: Boolean): Result<List<GroupInfo>> =
        groupRepository.getMyGroups(category = category, status = status)
}
