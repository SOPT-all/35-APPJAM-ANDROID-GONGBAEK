package com.sopt.gongbaek.presentation.util.timetable

/**
 * "HH:MM" 형식으로 시간 레이블 목록을 생성합니다.
 * @param startHour 시작 시간 (기본값: 9)
 * @param count 생성할 레이블 개수 (기본값: 19)
 * @return 생성된 레이블 목록
 */
fun generateTimeLabels(
    startHour: Int = 9,
    count: Int = 19
): List<String> =
    List(count) { index ->
        val hour = startHour + index / 2
        val minute = if (index % 2 == 0) "00" else "30"
        "$hour:$minute"
    }
