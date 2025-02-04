package com.sopt.gongbaek.di

import android.content.Context
import android.net.ConnectivityManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.gongbaek.BuildConfig
import com.sopt.gongbaek.BuildConfig.DEBUG
import com.sopt.gongbaek.data.local.datasource.TokenLocalDataSource
import com.sopt.gongbaek.data.remote.util.AuthInterceptor
import com.sopt.gongbaek.data.remote.util.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesJson(): Json =
        Json {
            isLenient = true
            prettyPrint = true
            explicitNulls = false
            ignoreUnknownKeys = true
        }

    @Provides
    @Singleton
    fun provideConverterFactory(json: Json): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        tokenLocalDataSource: TokenLocalDataSource,
        @ApplicationContext context: Context
    ): AuthInterceptor {
        return AuthInterceptor(tokenLocalDataSource, context)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(10L, TimeUnit.SECONDS)
            writeTimeout(10L, TimeUnit.SECONDS)
            readTimeout(10L, TimeUnit.SECONDS)
            if (DEBUG) addInterceptor(loggingInterceptor)
            addInterceptor(authInterceptor)
        }.build()

    @Provides
    @Singleton
    fun provideGongBaekBaseUrl(): String = BuildConfig.GONGBAEK_BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return NetworkManager(connectivityManager)
    }
}
