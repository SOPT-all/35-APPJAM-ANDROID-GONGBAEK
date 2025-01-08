package com.sopt.gongbaek.di

import com.sopt.gongbaek.data.local.datasource.ExampleLocalDataSource
import com.sopt.gongbaek.data.local.datasourceimpl.ExampleLocalDataSourceImpl
import com.sopt.gongbaek.data.remote.datasource.ExampleRemoteDataSource
import com.sopt.gongbaek.data.remote.datasourceimpl.ExampleRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindExampleLocalDataSource(
        exampleLocalDataSourceImpl: ExampleLocalDataSourceImpl
    ): ExampleLocalDataSource

    @Binds
    @Singleton
    abstract fun bindExampleRemoteDataSource(
        exampleRemoteDataSourceImpl: ExampleRemoteDataSourceImpl
    ): ExampleRemoteDataSource
}
