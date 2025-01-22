package com.sopt.gongbaek.domain.repository

import com.sopt.gongbaek.domain.model.UserAuth
import com.sopt.gongbaek.domain.model.UserInfo

interface AuthRepository {
    suspend fun registerUserInfo(userInfo: UserInfo): Result<UserAuth>
}
