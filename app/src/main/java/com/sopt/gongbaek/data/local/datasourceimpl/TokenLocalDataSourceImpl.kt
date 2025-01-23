package com.sopt.gongbaek.data.local.datasourceimpl

import android.content.SharedPreferences
import com.sopt.gongbaek.data.local.datasource.TokenLocalDataSource
import com.sopt.gongbaek.di.qualifier.TokenPrefs
import javax.inject.Inject

class TokenLocalDataSourceImpl @Inject constructor(
    @TokenPrefs private val sharedPreferences: SharedPreferences
) : TokenLocalDataSource {
    override var accessToken: String
        get() = sharedPreferences.getString(ACCESS_TOKEN, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyNiIsImV4cCI6MTczNzc1NzYyNCwidXNlcklkIjoyNn0.bwgS0uLVQWFAzi2OatSkVYD5VqjM_wKOnJ8HzR736sArko3RdzRsabSqLCN9-UEP5YdKyBgjz9hAo1-Fe2-xBA") ?: ""
        set(value) = sharedPreferences.edit().putString(ACCESS_TOKEN, value).apply()

    override var refreshToken: String
        get() = sharedPreferences.getString(REFRESH_TOKEN, "") ?: ""
        set(value) = sharedPreferences.edit().putString(REFRESH_TOKEN, value).apply()

    override fun clearInfo() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
    }
}
