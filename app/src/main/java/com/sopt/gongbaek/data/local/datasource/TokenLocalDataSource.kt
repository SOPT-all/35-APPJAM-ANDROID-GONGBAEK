package com.sopt.gongbaek.data.local.datasource

interface TokenLocalDataSource {
    var accessToken: String
    var refreshToken: String
    fun clearInfo()
}
