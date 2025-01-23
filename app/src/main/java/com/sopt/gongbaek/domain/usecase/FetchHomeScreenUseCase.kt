package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.repository.GroupRepository

class FetchHomeScreenUseCase(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke() = groupRepository.getNearestGroup()
}
