package com.sopt.gongbaek.presentation.util.timetable

fun groupConsecutiveIndices(indices: List<Int>): List<List<Int>> {
    if (indices.isEmpty()) return emptyList()

    fun isConsecutive(a: Int, b: Int): Boolean = b == a + 1

    val result = mutableListOf<MutableList<Int>>()
    var currentGroup = mutableListOf(indices[0])

    for (i in 1 until indices.size) {
        if (isConsecutive(indices[i - 1], indices[i])) {
            currentGroup.add(indices[i])
        } else {
            result.add(currentGroup)
            currentGroup = mutableListOf(indices[i])
        }
    }
    result.add(currentGroup)

    return result.map { it.toList() }
}
