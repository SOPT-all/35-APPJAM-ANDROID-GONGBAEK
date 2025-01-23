package com.sopt.gongbaek.di

import com.sopt.gongbaek.data.local.datasource.ExampleLocalDataSource
import com.sopt.gongbaek.data.local.datasource.LectureTimetableLocalDataSource
import com.sopt.gongbaek.data.local.datasource.TokenLocalDataSource
import com.sopt.gongbaek.data.local.datasourceimpl.ExampleLocalDataSourceImpl
import com.sopt.gongbaek.data.local.datasourceimpl.LectureTimetableLocalDataSourceImpl
import com.sopt.gongbaek.data.local.datasourceimpl.TokenLocalDataSourceImpl
import com.sopt.gongbaek.data.remote.datasource.AuthRemoteDataSource
import com.sopt.gongbaek.data.remote.datasource.ExampleRemoteDataSource
import com.sopt.gongbaek.data.remote.datasource.GroupRemoteDataSource
import com.sopt.gongbaek.data.remote.datasource.SearchRemoteDataSource
import com.sopt.gongbaek.data.remote.datasourceimpl.AuthRemoteDatasourceImpl
import com.sopt.gongbaek.data.remote.datasourceimpl.ExampleRemoteDataSourceImpl
import com.sopt.gongbaek.data.remote.datasourceimpl.GroupRemoteDataSourceImpl
import com.sopt.gongbaek.data.remote.datasourceimpl.SearchRemoteDataSourceImpl
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

    @Binds
    @Singleton
    abstract fun bindTokenLocalDataSource(
        tokenLocalDataSourceImpl: TokenLocalDataSourceImpl
    ): TokenLocalDataSource

    @Binds
    @Singleton
    abstract fun bindGroupRemoteDataSource(
        groupRemoteDataSourceImpl: GroupRemoteDataSourceImpl
    ): GroupRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindSearchRemoteDataSource(
        searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl
    ): SearchRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDatasourceImpl
    ): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLectureTimetableLocalDataSource(
        lectureTimetableDataSourceImpl: LectureTimetableLocalDataSourceImpl
    ): LectureTimetableLocalDataSource
}
