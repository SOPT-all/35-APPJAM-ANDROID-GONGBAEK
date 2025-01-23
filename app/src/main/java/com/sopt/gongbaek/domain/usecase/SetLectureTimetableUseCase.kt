package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.repository.LectureTimetableRepository

class SetLectureTimetableUseCase(
    private val lectureTimetableRepository: LectureTimetableRepository
) {
    operator fun invoke(lectureTimetable: Map<String, List<Int>>) = lectureTimetableRepository.setTimetable(lectureTimetable)
}
