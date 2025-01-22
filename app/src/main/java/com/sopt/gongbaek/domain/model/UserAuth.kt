package com.sopt.gongbaek.domain.model

data class UserAuth(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String
)
