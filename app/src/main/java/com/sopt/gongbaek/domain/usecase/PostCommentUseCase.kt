package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.model.Comment
import com.sopt.gongbaek.domain.model.GroupComments
import com.sopt.gongbaek.domain.repository.CommentRepository
import kotlinx.coroutines.coroutineScope

class PostCommentUseCase(
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(comment: Comment): Result<GroupComments> = coroutineScope {
        try {
            // 1. 먼저 POST 요청을 실행
            val postCommentResult = commentRepository.postComment(comment = comment)
            if (postCommentResult.isFailure) {
                return@coroutineScope Result.failure(
                    Exception("Failed to post comment: ${postCommentResult.exceptionOrNull()?.message}")
                )
            }

            // 2. POST 요청 성공 후에 GET 요청 실행
            val getGroupCommentsResult = commentRepository.getGroupComments(
                isPublic = comment.isPublic,
                groupId = comment.groupId,
                groupType = comment.cycle
            )
            if (getGroupCommentsResult.isFailure) {
                return@coroutineScope Result.failure(
                    Exception("Failed to get comments: ${getGroupCommentsResult.exceptionOrNull()?.message}")
                )
            }

            // 3. 모든 작업 성공 시 결과 반환
            Result.success(getGroupCommentsResult.getOrThrow())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
