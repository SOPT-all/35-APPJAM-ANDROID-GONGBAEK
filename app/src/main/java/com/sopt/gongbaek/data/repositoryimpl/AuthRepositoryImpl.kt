package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.mapper.todata.toData
import com.sopt.gongbaek.data.mapper.todomain.toDomain
import com.sopt.gongbaek.data.remote.datasource.AuthRemoteDataSource
import com.sopt.gongbaek.data.remote.util.handleApiResponse
import com.sopt.gongbaek.data.remote.util.handleNullableApiResponse
import com.sopt.gongbaek.domain.model.UserAuth
import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDatasource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun registerUserInfo(userInfo: UserInfo): Result<UserAuth> =
        runCatching {
            authRemoteDatasource.registerUserInfo(
                registerUserInfoRequestDto = userInfo.toData()
            ).handleApiResponse().getOrThrow().toDomain()
        }

    override suspend fun validateNickname(nickname: String): Result<Unit> =
        runCatching {
            authRemoteDatasource.validateNickname(nickname = nickname).handleNullableApiResponse().getOrThrow()
        }
}
