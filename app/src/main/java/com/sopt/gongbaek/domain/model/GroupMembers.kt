package com.sopt.gongbaek.domain.model

data class GroupMembers(
    val maxPeopleCount: Int = 0,
    val currentPeopleCount: Int = 0,
    val members: List<Member> = listOf()
) {
    data class Member(
        val profileImg: Int = 0,
        val nickname: String = "",
        val isHost: Boolean = false
    )
}
