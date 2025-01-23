package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.repository.GroupRepository

class GetGroupsUseCase(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(category: String?): Result<List<GroupInfo>> =
        groupRepository.getGroups(category = category)
}
