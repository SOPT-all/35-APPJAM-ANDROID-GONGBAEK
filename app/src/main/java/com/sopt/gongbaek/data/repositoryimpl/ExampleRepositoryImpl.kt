package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.mapper.todata.toData
import com.sopt.gongbaek.data.mapper.todomain.toDomain
import com.sopt.gongbaek.data.remote.datasource.ExampleRemoteDataSource
import com.sopt.gongbaek.data.remote.util.handleApiResponse
import com.sopt.gongbaek.domain.model.ExampleModel
import com.sopt.gongbaek.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val exampleRemoteDataSource: ExampleRemoteDataSource
) : ExampleRepository {
    override suspend fun getExampleData(): Result<ExampleModel> {
        return runCatching {
            exampleRemoteDataSource.getExampleData().handleApiResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun postExampleData(exampleModel: ExampleModel): Result<Unit> {
        return runCatching {
            exampleRemoteDataSource.postExampleData(exampleModel.toData()).handleApiResponse().getOrThrow()
        }
    }
}