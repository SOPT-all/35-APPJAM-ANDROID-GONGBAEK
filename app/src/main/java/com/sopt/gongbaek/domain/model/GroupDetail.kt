package com.sopt.gongbaek.domain.model

data class GroupDetail(
    val groupInfo: GroupInfo = GroupInfo(),
    val groupHost: GroupHost = GroupHost(),
    val groupComments: GroupComments = GroupComments()
)
