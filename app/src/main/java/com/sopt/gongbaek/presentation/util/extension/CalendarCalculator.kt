package com.sopt.gongbaek.presentation.util.extension

import java.time.LocalDate

object CalendarCalculator {
    fun calculateDateForCell(
        cellIndex: Int,
        startDayOfWeek: Int,
        firstDayOfMonth: LocalDate,
        daysInPreviousMonth: Int,
        visibleMonth: LocalDate
    ): LocalDate {
        val day = cellIndex - startDayOfWeek + 1
        return when {
            cellIndex < startDayOfWeek -> {
                visibleMonth.minusMonths(1).withDayOfMonth(daysInPreviousMonth - startDayOfWeek + cellIndex + 1)
            }
            day > firstDayOfMonth.lengthOfMonth() -> {
                visibleMonth.plusMonths(1).withDayOfMonth(day - firstDayOfMonth.lengthOfMonth())
            }
            else -> {
                firstDayOfMonth.plusDays((day - 1).toLong())
            }
        }
    }
}
