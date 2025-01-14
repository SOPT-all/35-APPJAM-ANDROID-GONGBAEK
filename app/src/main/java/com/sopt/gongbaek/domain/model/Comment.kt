package com.sopt.gongbaek.domain.model

data class Comment(
    val groupId: Int,
    val groupType: String,
    val commentId: Int,
    val commentWriter: String,
    val commentContent: String,
    val createdAt: String,
    val isGroupHost: Boolean,
    val isWriter: Boolean
) {
    fun getCreatedYear(): String = createdAt.split("-")[0]

    fun getCreatedMonth(): String = createdAt.split("-")[1]

    fun getCreatedDay(): String = createdAt.split("-")[2]

    fun getCreatedHour(): String = createdAt.split("-")[3]

    fun getCreatedMinute(): String = createdAt.split("-")[4]
}
