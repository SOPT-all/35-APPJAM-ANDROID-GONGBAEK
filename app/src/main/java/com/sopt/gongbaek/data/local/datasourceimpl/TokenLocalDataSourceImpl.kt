package com.sopt.gongbaek.data.local.datasourceimpl

import android.content.SharedPreferences
import com.sopt.gongbaek.data.local.datasource.TokenLocalDataSource
import javax.inject.Inject

class TokenLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : TokenLocalDataSource {
    override var accessToken: String
        get() = sharedPreferences.getString(ACCESS_TOKEN, "") ?: "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxODIzNDQyODE0LCJ1c2VySWQiOjF9.piiJ8o2pXY-_A6ZjvxQ7MOyfnYOsnM-FQwMmC3m4qWIzK0wlxBb6tEfT3HzLuIU1SoZTveGeCEq7g6JABYmx2A"
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
