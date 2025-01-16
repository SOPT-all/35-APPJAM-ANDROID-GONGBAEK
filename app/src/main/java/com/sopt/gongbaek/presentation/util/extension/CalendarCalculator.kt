package com.sopt.gongbaek.presentation.util.extension

import java.time.LocalDate

fun calculateDateForCell(
    cellIndex: Int,
    startDayOfWeek: Int,
    firstDayOfMonth: LocalDate,
    daysInPreviousMonth: Int,
    selectedMonth: LocalDate
): LocalDate {
    val day = cellIndex - startDayOfWeek + 1
    return when {
        cellIndex < startDayOfWeek -> {
            selectedMonth.minusMonths(1).withDayOfMonth(daysInPreviousMonth - startDayOfWeek + cellIndex + 1)
        }

        day > firstDayOfMonth.lengthOfMonth() -> {
            selectedMonth.plusMonths(1).withDayOfMonth(day - firstDayOfMonth.lengthOfMonth())
        }

        else -> {
            firstDayOfMonth.plusDays((day - 1).toLong())
        }
    }
}
