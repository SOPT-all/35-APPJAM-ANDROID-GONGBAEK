package com.sopt.gongbaek.presentation.util.timetable

/**
 * 연속된 인덱스를 그룹으로 묶어 반환합니다.
 * 예를 들어, [1, 2, 3, 5, 6, 7, 9]가 주어지면 [[1, 2, 3], [5, 6, 7], [9]]를 반환합니다.
 * @param indices 그룹으로 묶을 인덱스 리스트
 * @return 그룹으로 묶인 인덱스 리스트
 */
fun groupConsecutiveIndices(indices: List<Int>): List<List<Int>> {
    if (indices.isEmpty()) return emptyList()

    return indices.fold(mutableListOf<MutableList<Int>>()) { result, current ->
        if (shouldStartNewGroup(result, current)) {
            // 새 그룹을 생성
            result.add(mutableListOf(current))
        } else {
            // 기존 그룹에 추가
            result.last().add(current)
        }
        result
    }.map { it.toList() }
}

/**
 * 새 그룹을 시작해야 하는지 여부를 반환합니다.
 * @param result 현재까지 생성된 그룹 리스트
 * @param current 현재 인덱스
 * @return 새 그룹을 시작해야 하는지 여부
 */
private fun shouldStartNewGroup(
    result: List<List<Int>>,
    current: Int
): Boolean =
    result.isEmpty() || current != result.last().last() + 1
