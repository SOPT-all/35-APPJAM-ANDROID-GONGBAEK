package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.repository.GroupRepository

class FetchLatestGroupUseCase(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(groupType: String) = groupRepository.getLatestGroup(groupType)
}
