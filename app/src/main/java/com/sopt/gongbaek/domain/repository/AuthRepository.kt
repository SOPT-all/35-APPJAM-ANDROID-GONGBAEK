package com.sopt.gongbaek.domain.repository

import com.sopt.gongbaek.domain.model.UserAuth
import com.sopt.gongbaek.domain.model.UserInfo
import com.sopt.gongbaek.domain.model.UserProfile

interface AuthRepository {
    suspend fun registerUserInfo(userInfo: UserInfo): Result<UserAuth>
    suspend fun validateNickname(nickname: String): Result<Unit>
    suspend fun getUserProfile(): Result<UserProfile>
}
