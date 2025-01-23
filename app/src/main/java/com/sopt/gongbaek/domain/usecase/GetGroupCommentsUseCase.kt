package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.model.GroupComments
import com.sopt.gongbaek.domain.repository.CommentRepository

class GetGroupCommentsUseCase(
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(isPublic: Boolean, groupId: Int, groupType: String): Result<GroupComments> =
        commentRepository.getGroupComments(isPublic = isPublic, groupId = groupId, groupType = groupType)
}
