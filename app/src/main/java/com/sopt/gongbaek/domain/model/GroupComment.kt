package com.sopt.gongbaek.domain.model

data class GroupComment(
    val groupId: Int,
    val groupStatus: String,
    val groupCycle: String,
    val commentCount: Int,
    val commentList: List<Comment>
) {
    data class Comment(
        val commentId: Int,
        val commentWriter: String,
        val commentContent: String,
        val createdAt: String,
        val isGroupHost: Boolean,
        val isWriter: Boolean
    ) {
        fun getCreatedMonth(): String = createdAt.split("-")[1]

        fun getCreatedDay(): String = createdAt.split("-")[2]

        fun getCreatedHour(): String = createdAt.split("-")[3]

        fun getCreatedMinute(): String = createdAt.split("-")[4]
    }
}
