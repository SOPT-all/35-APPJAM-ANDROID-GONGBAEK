package com.sopt.gongbaek.presentation.util.timetable

import com.sopt.gongbaek.domain.model.UserLectureTimeTable

fun convertToSlotsByDay(timeTables: List<UserLectureTimeTable>): Map<String, List<Int>> {
    return timeTables
        .filter { it.startTime >= 9.0 && it.endTime <= 18.0 && it.startTime < it.endTime }
        .groupBy { it.weekDay }
        .mapValues { (_, timeTableList) ->
            timeTableList.flatMap { timeTable ->
                val startSlot = ((timeTable.startTime - 9) * 2).toInt()
                val endSlot = ((timeTable.endTime - 9) * 2).toInt()
                (startSlot until endSlot).toList()
            }.sorted()
        }
}
