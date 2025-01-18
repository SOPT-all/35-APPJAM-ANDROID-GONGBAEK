package com.sopt.gongbaek.domain.type

enum class GroupCategoryType(
    val description: String
) {
    STUDY(
        description = "스터디"
    ),
    DINING(
        description = "식사"
    ),
    EXERCISE(
        description = "운동/산책"
    ),
    PLAYING(
        description = "취미/오락"
    ),
    NETWORKING(
        description = "네트워킹"
    ),
    OTHERS(
        description = "기타"
    ),
    ALL(
        description = "전체"
    )
}
