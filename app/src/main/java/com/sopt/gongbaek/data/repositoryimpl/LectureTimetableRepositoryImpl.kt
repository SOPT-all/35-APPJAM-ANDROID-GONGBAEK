package com.sopt.gongbaek.data.repositoryimpl

import com.sopt.gongbaek.data.local.datasource.LectureTimetableLocalDataSource
import com.sopt.gongbaek.domain.repository.LectureTimetableRepository
import javax.inject.Inject

class LectureTimetableRepositoryImpl @Inject constructor(
    private val lectureTimetableLocalDataSource: LectureTimetableLocalDataSource
) : LectureTimetableRepository {
    override fun getTimetable(): Map<String, List<Int>> =
        lectureTimetableLocalDataSource.getTimetable()

    override fun setTimetable(lectureTimetable: Map<String, List<Int>>) {
        lectureTimetableLocalDataSource.setTimetable(lectureTimetable)
    }
}
