package com.sopt.gongbaek.domain.model

data class GroupComment(
    val groupId: Int = 0,
    val groupStatus: String = "",
    val groupCycle: String = "",
    val commentCount: Int = 0,
    val commentList: List<Comment> = listOf()
) {
    data class Comment(
        val commentId: Int = 0,
        val commentWriter: String = "",
        val commentContent: String = "",
        val createdAt: String = "",
        val isGroupHost: Boolean = false,
        val isWriter: Boolean = false
    ) {
        fun getCreatedMonth(): String = createdAt.split("-")[1]

        fun getCreatedDay(): String = createdAt.split("-")[2]

        fun getCreatedHour(): String = createdAt.split("-")[3]

        fun getCreatedMinute(): String = createdAt.split("-")[4]
    }
}
