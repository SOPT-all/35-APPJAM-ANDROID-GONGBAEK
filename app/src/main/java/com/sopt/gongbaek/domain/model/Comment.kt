package com.sopt.gongbaek.domain.model

data class Comment(
    val groupId: Int,
    val cycle: String,
    val isPublic: Boolean,
    val content: String
)
