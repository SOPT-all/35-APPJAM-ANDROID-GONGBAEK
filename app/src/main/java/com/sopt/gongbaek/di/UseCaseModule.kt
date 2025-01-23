package com.sopt.gongbaek.di

import com.sopt.gongbaek.domain.repository.ExampleRepository
import com.sopt.gongbaek.domain.repository.GroupRepository
import com.sopt.gongbaek.domain.usecase.ExamplePostUseCase
import com.sopt.gongbaek.domain.usecase.FetchHomeScreenUseCase
import com.sopt.gongbaek.domain.usecase.FetchLatestGroupUseCase
import com.sopt.gongbaek.domain.usecase.GetGroupsUseCase
import com.sopt.gongbaek.domain.usecase.GetMyGroupsUseCase
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
    fun provideGetMyGroupsUseCase(
        groupRepository: GroupRepository
    ): GetMyGroupsUseCase = GetMyGroupsUseCase(groupRepository)

    @Provides
    @Singleton
    fun provideGetGroupsUseCase(
        groupRepository: GroupRepository
    ): GetGroupsUseCase = GetGroupsUseCase(groupRepository)

    @Provides
    @Singleton
    fun provideFetchHomeScreenUseCase(
        groupRepository: GroupRepository
    ): FetchHomeScreenUseCase = FetchHomeScreenUseCase(groupRepository)

    @Provides
    @Singleton
    fun provideFetchLatestGroupUseCase(
        groupRepository: GroupRepository
    ): FetchLatestGroupUseCase = FetchLatestGroupUseCase(groupRepository)
}
