package com.sopt.gongbaek.domain.repository

interface LectureTimetableRepository {
    fun getTimetable(): Map<String, List<Int>>
    fun setTimetable(lectureTimetable: Map<String, List<Int>>)
}
