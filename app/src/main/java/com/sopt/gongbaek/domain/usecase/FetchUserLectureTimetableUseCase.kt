package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.model.UserLectureTimeTable
import com.sopt.gongbaek.domain.repository.AuthRepository

class FetchUserLectureTimetableUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<List<UserLectureTimeTable>> =
        authRepository.getUserLectureTimeTable()
}
