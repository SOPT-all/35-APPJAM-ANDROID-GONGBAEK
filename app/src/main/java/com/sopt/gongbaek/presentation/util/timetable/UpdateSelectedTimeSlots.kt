package com.sopt.gongbaek.presentation.util.timetable

fun updateSelectedTimeSlots(
    currentIndices: List<Int>,
    clickedIndex: Int
): List<Int> =
    if (clickedIndex in currentIndices) {
        currentIndices.minus(clickedIndex)
    } else {
        currentIndices.plus(clickedIndex)
    }
