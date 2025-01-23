package com.sopt.gongbaek.presentation.util.timetable

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun nearestGroupFormatSchedule(
    weekDate: String,
    startTime: Double,
    endTime: Double
): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREAN)
        val date = LocalDate.parse(weekDate, formatter)
        val formattedDate = date.format(DateTimeFormatter.ofPattern("M/d E요일", Locale.KOREAN))
        "$formattedDate ${formatTime(startTime)}-${formatTime(endTime)}"
    } catch (e: Exception) {
        "Invalid date input"
    }
}

private fun formatTime(time: Double): String {
    val hours = time.toInt()
    val minutes = ((time - hours) * 60).toInt()
    return "%02d시 %02d분".format(hours, minutes)
}
