package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.repository.TokenRepository

class SetTokenUseCase(
    private val tokenRepository: TokenRepository
) {
    operator fun invoke(accessToken: String, refreshToken: String) =
        tokenRepository.setTokens(accessToken, refreshToken)
}
