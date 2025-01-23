package com.sopt.gongbaek.di

import com.sopt.gongbaek.domain.repository.AuthRepository
import com.sopt.gongbaek.domain.repository.CommentRepository
import com.sopt.gongbaek.domain.repository.ExampleRepository
import com.sopt.gongbaek.domain.repository.SearchRepository
import com.sopt.gongbaek.domain.repository.GroupRepository
import com.sopt.gongbaek.domain.repository.TokenRepository
import com.sopt.gongbaek.domain.usecase.ApplyGroupUseCase
import com.sopt.gongbaek.domain.usecase.ExamplePostUseCase
import com.sopt.gongbaek.domain.usecase.GetSearchMajorsResultUseCase
import com.sopt.gongbaek.domain.usecase.GetGroupsUseCase
import com.sopt.gongbaek.domain.usecase.GetGroupCommentsUseCase
import com.sopt.gongbaek.domain.usecase.GetMyGroupsUseCase
import com.sopt.gongbaek.domain.usecase.GetSearchUniversitiesResultUseCase
import com.sopt.gongbaek.domain.usecase.RegisterUserInfoUseCase
import com.sopt.gongbaek.domain.usecase.SetTokenUseCase
import com.sopt.gongbaek.domain.usecase.ValidateNicknameUseCase
import com.sopt.gongbaek.domain.usecase.PostGroupUseCase
import com.sopt.gongbaek.domain.usecase.LoadGroupDetailScreenUseCase
import com.sopt.gongbaek.domain.usecase.PostCommentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideExamplePostUseCase(
        exampleRepository: ExampleRepository
    ): ExamplePostUseCase = ExamplePostUseCase(exampleRepository)

    @Provides
    @Singleton
    fun provideGetSearchUniversitiesResultUseCase(
        searchRepository: SearchRepository
    ): GetSearchUniversitiesResultUseCase = GetSearchUniversitiesResultUseCase(searchRepository)

    @Provides
    @Singleton
    fun provideGetSearchMajorsResultUseCase(
        searchRepository: SearchRepository
    ): GetSearchMajorsResultUseCase = GetSearchMajorsResultUseCase(searchRepository)

    @Provides
    @Singleton
    fun provideGetMyGroupsUseCase(
        groupRepository: GroupRepository
    ): GetMyGroupsUseCase = GetMyGroupsUseCase(groupRepository)

    @Provides
    @Singleton
    fun provideLoadGroupDetailScreenUseCase(
        groupRepository: GroupRepository,
        commentRepository: CommentRepository
    ): LoadGroupDetailScreenUseCase = LoadGroupDetailScreenUseCase(groupRepository, commentRepository)

    @Provides
    @Singleton
    fun provideApplyGroupUseCase(
        groupRepository: GroupRepository
    ): ApplyGroupUseCase = ApplyGroupUseCase(groupRepository)

    @Provides
    @Singleton
    fun provideGetGroupCommentsUseCase(
        commentRepository: CommentRepository
    ): GetGroupCommentsUseCase = GetGroupCommentsUseCase(commentRepository)

    @Provides
    @Singleton
    fun providesPostCommentUseCase(
        commentRepository: CommentRepository
    ): PostCommentUseCase = PostCommentUseCase(commentRepository)

    @Provides
    @Singleton
    fun provideRegisterUserInfoUseCase(
        authRepository: AuthRepository
    ): RegisterUserInfoUseCase = RegisterUserInfoUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSetTokenUseCase(
        tokenRepository: TokenRepository
    ): SetTokenUseCase = SetTokenUseCase(tokenRepository)

    @Provides
    @Singleton
    fun provideValidateNicknameUseCase(
        authRepository: AuthRepository
    ): ValidateNicknameUseCase = ValidateNicknameUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGetGroupsUseCase(
        groupRepository: GroupRepository
    ): GetGroupsUseCase = GetGroupsUseCase(groupRepository)

    @Provides
    @Singleton
    fun providePostGroupUseCase(
        groupRepository: GroupRepository
    ): PostGroupUseCase = PostGroupUseCase(groupRepository)
}
