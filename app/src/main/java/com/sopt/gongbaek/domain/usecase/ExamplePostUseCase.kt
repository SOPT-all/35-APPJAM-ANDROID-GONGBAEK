package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.model.ExampleModel
import com.sopt.gongbaek.domain.repository.ExampleRepository

class ExamplePostUseCase(
    private val exampleRepository: ExampleRepository
) {
    suspend operator fun invoke(exampleModel: ExampleModel): Result<Unit> =
        exampleRepository.postExampleData(exampleModel = exampleModel)
}
