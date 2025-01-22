package com.sopt.gongbaek.di

import com.sopt.gongbaek.data.repositoryimpl.CommentRepositoryImpl
import com.sopt.gongbaek.data.repositoryimpl.ExampleRepositoryImpl
import com.sopt.gongbaek.data.repositoryimpl.GroupRepositoryImpl
import com.sopt.gongbaek.data.repositoryimpl.TokenRepositoryImpl
import com.sopt.gongbaek.domain.repository.CommentRepository
import com.sopt.gongbaek.domain.repository.ExampleRepository
import com.sopt.gongbaek.domain.repository.GroupRepository
import com.sopt.gongbaek.domain.repository.TokenRepository
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
    abstract fun bindTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): TokenRepository

    @Binds
    @Singleton
    abstract fun bindGroupRepository(
        groupRepositoryImpl: GroupRepositoryImpl
    ): GroupRepository

    @Binds
    @Singleton
    abstract fun bindCommentRepository(
        commentRepositoryImpl: CommentRepositoryImpl
    ): CommentRepository
}
