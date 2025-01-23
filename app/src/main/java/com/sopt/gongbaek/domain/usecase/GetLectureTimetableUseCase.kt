package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.repository.LectureTimetableRepository

class GetLectureTimetableUseCase(
    private val lectureTimetableRepository: LectureTimetableRepository
) {
    operator fun invoke(): Map<String, List<Int>> = lectureTimetableRepository.getTimetable()
}
