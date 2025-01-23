package com.sopt.gongbaek.presentation.util.timetable

import com.sopt.gongbaek.domain.model.TimeTable

fun convertToTimeTable(timeSlotsByDay: Map<String, List<Int>>): List<TimeTable> =
    timeSlotsByDay.flatMap { (day, slots) ->
        val englishDay = mapKoreanDayToEnglish(day)
        if (englishDay.isNotEmpty() && slots.isNotEmpty()) {
            processDaySlots(englishDay, slots)
        } else {
            emptyList()
        }
    }

private fun processDaySlots(englishDay: String, slots: List<Int>): List<TimeTable> {
    val sortedSlots = slots.sorted()
    val timeTableList = mutableListOf<TimeTable>()

    var start = sortedSlots[0]
    var end = start

    for (i in 1 until sortedSlots.size) {
        if (sortedSlots[i] == end + 1) {
            end = sortedSlots[i]
        } else {
            timeTableList.add(
                TimeTable(
                    weekDay = englishDay,
                    startTime = start.toDouble() / 2 + 9,
                    endTime = end.toDouble() / 2 + 9.5F
                )
            )
            start = sortedSlots[i]
            end = start
        }
    }

    timeTableList.add(
        TimeTable(
            weekDay = englishDay,
            startTime = start.toDouble() / 2 + 9,
            endTime = end.toDouble() / 2 + 9.5F
        )
    )

    return timeTableList
}

private fun mapKoreanDayToEnglish(day: String): String {
    return when (day) {
        "월" -> "MON"
        "화" -> "TUE"
        "수" -> "WED"
        "목" -> "THU"
        "금" -> "FRI"
        "토" -> "SAT"
        "일" -> "SUN"
        else -> ""
    }
}
