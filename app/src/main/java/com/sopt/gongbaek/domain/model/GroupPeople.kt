package com.sopt.gongbaek.domain.model

data class GroupPeople(
    val maxPeopleCount: Int,
    val currentPeopleCount: Int,
    val members: List<Member>
) {
    data class Member(
        val profileImg: Int,
        val nickname: String,
        val isHost: Boolean
    )
}
