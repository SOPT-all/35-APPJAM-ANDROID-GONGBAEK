package com.sopt.gongbaek.presentation.util.extension

import java.time.LocalDate

object CalendarUtils {
    fun calculateTotalCells(firstDayOfMonth: LocalDate, daysInMonth: Int): Int {
        val startDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
        return (startDayOfWeek + daysInMonth + 6) / 7 * 7
    }

    fun isWeekend(date: LocalDate): Boolean {
        return date.dayOfWeek.value in listOf(6, 7)
    }

    fun isOtherMonth(date: LocalDate, visibleMonth: LocalDate): Boolean {
        return date.monthValue != visibleMonth.monthValue || date.year != visibleMonth.year
    }

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
