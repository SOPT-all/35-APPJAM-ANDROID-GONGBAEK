package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.local.datasource.TokenLocalDataSource
import com.sopt.gongbaek.domain.repository.TokenRepository
import timber.log.Timber
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource
) : TokenRepository {
    override fun getAccessToken(): String = tokenLocalDataSource.accessToken

    override fun getRefreshToken(): String = tokenLocalDataSource.refreshToken

    override fun setTokens(accessToken: String, refreshToken: String) {
        tokenLocalDataSource.accessToken = accessToken
        tokenLocalDataSource.refreshToken = refreshToken
        Timber.tag("TokenRepository").d("Access Token Saved: $accessToken")
        Timber.tag("TokenRepository").d("Refresh Token Saved: $refreshToken")
    }

    override fun clearInfo() = tokenLocalDataSource.clearInfo()
}
