package com.sopt.gongbaek.presentation.util.timetable

fun startSelection(
    index: Int,
    validGroups: List<List<Int>>,
    onTimeSlotClick: (List<Int>) -> Unit
): Int? {
    val currentGroup = findGroupContainingIndex(index, validGroups)

    return currentGroup?.let {
        notifyTimeSlotClick(index, onTimeSlotClick)
        index
    }
}

fun expandSelection(
    index: Int,
    validGroups: List<List<Int>>,
    startIndex: Int,
    selectedTimeSlots: List<Int>,
    onTimeSlotClick: (List<Int>) -> Unit
): Int? {
    val startGroup = findGroupContainingIndex(startIndex, validGroups)
    val currentGroup = findGroupContainingIndex(index, validGroups)

    return if (canExpandSelection(startGroup, currentGroup)) {
        val updatedSelection = calculateUpdatedSelection(index, startIndex, currentGroup!!, selectedTimeSlots)
        onTimeSlotClick(updatedSelection)
        startIndex
    } else {
        startSelection(index, validGroups, onTimeSlotClick)
    }
}

private fun findGroupContainingIndex(index: Int, validGroups: List<List<Int>>): List<Int>? =
    validGroups.find { it.contains(index) }

private fun notifyTimeSlotClick(index: Int, onTimeSlotClick: (List<Int>) -> Unit) =
    onTimeSlotClick(listOf(index))

private fun canExpandSelection(startGroup: List<Int>?, currentGroup: List<Int>?): Boolean =
    startGroup == currentGroup && currentGroup != null

private fun calculateUpdatedSelection(
    index: Int,
    startIndex: Int,
    currentGroup: List<Int>,
    selectedTimeSlots: List<Int>
): List<Int> {
    val range = if (startIndex < index) startIndex..index else index..startIndex
    val validIndices = range.filter { it in currentGroup }
    return (selectedTimeSlots + validIndices).distinct().sorted()
}
