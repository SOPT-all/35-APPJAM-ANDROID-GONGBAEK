package com.sopt.gongbaek.domain.repository

import com.sopt.gongbaek.domain.model.Comment
import com.sopt.gongbaek.domain.model.GroupComments

interface CommentRepository {
    suspend fun getGroupComments(isPublic: Boolean, groupId: Int, groupType: String): Result<GroupComments>
    suspend fun postComment(comment: Comment): Result<Int>
}
