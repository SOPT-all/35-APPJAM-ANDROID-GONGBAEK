package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.GroupMembersResponseDto
import com.sopt.gongbaek.domain.model.GroupMembers

fun GroupMembersResponseDto.toDomain() = GroupMembers(
    maxPeopleCount = maxPeopleCount,
    currentPeopleCount = currentPeopleCount,
    members = members.map { member ->
        GroupMembers.Member(
            profileImg = member.profileImg,
            nickname = member.nickname,
            isHost = member.isHost
        )
    }
)
