package com.sopt.gongbaek.data.remote.util

import android.content.Context
import com.sopt.gongbaek.data.local.datasource.TokenLocalDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource,
    @ApplicationContext private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return proceedWithAuthorization(chain, originalRequest)
    }

    private fun proceedWithAuthorization(chain: Interceptor.Chain, request: Request): Response {
        val authRequest = addAuthorizationHeader(request)
        return chain.proceed(authRequest)
    }

    private fun addAuthorizationHeader(request: Request): Request =
        request.newBuilder()
            .addHeader(AUTHORIZATION, "$BEARER ${tokenLocalDataSource.accessToken}")
            .build()

    companion object {
        private const val BEARER = "Bearer"
        private const val AUTHORIZATION = "Authorization"
    }
}
