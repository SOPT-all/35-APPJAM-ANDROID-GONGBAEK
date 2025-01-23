package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.data.remote.dto.response.GroupRegisterResponseDto
import com.sopt.gongbaek.domain.model.GroupRegisterInfo
import com.sopt.gongbaek.domain.repository.GroupRepository

class PostGroupUseCase(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(groupRegisterInfo: GroupRegisterInfo): Result<GroupRegisterResponseDto> =
        groupRepository.postGroup(groupRegisterInfo = groupRegisterInfo)
}