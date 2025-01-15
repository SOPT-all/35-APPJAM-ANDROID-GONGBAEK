package com.sopt.gongbaek.domain.type

enum class GroupCycleType(
    val description: String,
    val groupCycle: String
) {
    ONCE(
        description = "한번만 볼래요",
        groupCycle = "ONCE"
    ),
    WEEKLY(
        description = "매주 볼래요",
        groupCycle = "WEEKLY"
    );

    companion object {
        fun getGroupCycle(description: String) = when (description) {
            "한번만 볼래요" -> "ONCE"
            "매주 볼래요" -> "WEEKLY"
            else -> "UNKNOWN"
        }
    }
}
