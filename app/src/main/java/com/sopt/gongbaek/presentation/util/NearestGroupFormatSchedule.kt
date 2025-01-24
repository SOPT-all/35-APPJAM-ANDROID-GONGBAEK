package com.sopt.gongbaek.presentation.util

import com.sopt.gongbaek.domain.type.DayOfWeekType.Companion.toDayOfWeekRemoveSuffix
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

fun nearestGroupFormatSchedule(
    weekDate: String?,
    startTime: Double,
    endTime: Double
): String {
    val formattedDate = if (!weekDate.isNullOrBlank()) {
        try {
            val date = LocalDate.parse(weekDate)
            date.format(DateTimeFormatter.ofPattern("M/d E요일", Locale.KOREAN))
        } catch (e: DateTimeParseException) {
            "날짜 형식 오류"
        }
    } else {
        "날짜 없음"
    }

    return "$formattedDate ${formatTime(startTime)}-${formatTime(endTime)}"
}

fun homeOnceGroupFormatSchedule(
    weekDay: String,
    startTime: Double,
    endTime: Double
): String {
    val koreanDay = toDayOfWeekRemoveSuffix(weekDay, "")
    return "$koreanDay ${formatTime(startTime)}-${formatTime(endTime)}"
}

private fun formatTime(time: Double): String {
    val hours = time.toInt()
    val minutes = ((time - hours) * 60).toInt()
    if (minutes == 0) {
        return "%02d시".format(hours)
    }
    return "%02d시 %02d분".format(hours, minutes)
}
