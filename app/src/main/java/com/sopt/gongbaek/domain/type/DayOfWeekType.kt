package com.sopt.gongbaek.domain.type

enum class DayOfWeekType(
    val description: String,
    val dayOfWeek: String
) {
    MON(
        description = "월요일",
        dayOfWeek = "MON"
    ),
    TUE(
        description = "화요일",
        dayOfWeek = "TUE"
    ),
    WED(
        description = "수요일",
        dayOfWeek = "WED"
    ),
    THU(
        description = "목요일",
        dayOfWeek = "THU"
    ),
    FRI(
        description = "금요일",
        dayOfWeek = "FRI"
    ),
    ALL(
        description = "전체",
        dayOfWeek = "ALL"
    );

    companion object {
        fun toDayOfWeek(description: String) = when (description) {
            "월요일" -> "MON"
            "화요일" -> "TUE"
            "수요일" -> "WED"
            "목요일" -> "THU"
            "금요일" -> "FRI"
            else -> "UNKNOWN"
        }
    }
}
