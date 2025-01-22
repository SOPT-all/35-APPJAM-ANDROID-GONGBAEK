package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.mapper.todata.toData
import com.sopt.gongbaek.data.mapper.todomain.toDomain
import com.sopt.gongbaek.data.remote.datasource.AuthRemoteDataSource
import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.util.handleApiResponse
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
            authRemoteDatasource.validateNickname(nickname)
        }.fold(
            onSuccess = { Result.success(Unit) },
            onFailure = { exception ->
                when (exception.message) {
                    "HTTP 409" -> Result.failure(Exception("이미 사용중인 닉네임입니다."))
                    DUPLICATE_NICKNAME_ERROR_CODE -> Result.failure(Exception("이미 사용중인 닉네임입니다."))
                    NICKNAME_LENGTH_ERROR_CODE -> Result.failure(Exception("닉네임은 2자 이상 10자 이하로 입력해주세요."))
                    NICKNAME_KOREAN_ERROR_CODE -> Result.failure(Exception("닉네임은 한글만 입력 가능합니다."))
                    else -> Result.failure(exception)
                }
            }
        )

    companion object {
        private const val DUPLICATE_NICKNAME_ERROR_CODE = "4092"
        private const val NICKNAME_LENGTH_ERROR_CODE = "4005"
        private const val NICKNAME_KOREAN_ERROR_CODE = "4006"
    }
}
