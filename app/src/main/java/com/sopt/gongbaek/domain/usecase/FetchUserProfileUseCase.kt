package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.model.UserProfile
import com.sopt.gongbaek.domain.repository.AuthRepository

class FetchUserProfileUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<UserProfile> = authRepository.getUserProfile()
}
