package com.sopt.gongbaek.di

import com.sopt.gongbaek.data.repositoryimpl.ExampleRepositoryImpl
import com.sopt.gongbaek.data.repositoryimpl.SearchRepositoryImpl
import com.sopt.gongbaek.domain.repository.ExampleRepository
import com.sopt.gongbaek.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindExampleRepository(
        exampleRepositoryImpl: ExampleRepositoryImpl
    ): ExampleRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}
