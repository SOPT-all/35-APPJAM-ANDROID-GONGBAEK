package com.sopt.gongbaek.di

import com.sopt.gongbaek.data.remote.service.ExampleService
import com.sopt.gongbaek.di.qualifier.GongBaek
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideExampleService(@GongBaek retrofit: Retrofit): ExampleService =
        retrofit.create(ExampleService::class.java)
}