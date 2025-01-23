package com.sopt.gongbaek.data.local.datasource

interface LectureTimetableLocalDataSource {
    fun getTimetable(): Map<String, List<Int>>
    fun setTimetable(lectureTimetable: Map<String, List<Int>>)
}
