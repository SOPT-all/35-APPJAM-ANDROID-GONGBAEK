package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.repository.AuthRepository

class ValidateNicknameUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(nickname: String) = authRepository.validateNickname(nickname)
}
