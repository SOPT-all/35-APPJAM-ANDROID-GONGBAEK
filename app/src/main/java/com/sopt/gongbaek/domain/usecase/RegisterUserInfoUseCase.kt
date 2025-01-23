package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.model.UserAuth
import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.domain.repository.AuthRepository

class RegisterUserInfoUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userInfo: UserInfo): Result<UserAuth> =
        authRepository.registerUserInfo(userInfo)
}
