package com.sopt.gongbaek.domain.repository

import com.sopt.gongbaek.domain.model.ExampleModel

interface ExampleRepository {
    suspend fun getExampleData(): Result<ExampleModel>
    suspend fun postExampleData(exampleModel: ExampleModel): Result<Unit>
}
