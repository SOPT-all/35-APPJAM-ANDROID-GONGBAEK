package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.model.GroupDetail
import com.sopt.gongbaek.domain.repository.CommentRepository
import com.sopt.gongbaek.domain.repository.GroupRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class LoadGroupDetailScreenUseCase(
    private val groupRepository: GroupRepository,
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(groupId: Int, groupType: String): Result<GroupDetail> = coroutineScope {
        try {
            val groupInfoDeferred = async { groupRepository.getGroupDetail(groupId = groupId, groupType = groupType) }
            val groupHostDeferred = async { groupRepository.getGroupHost(groupId = groupId, groupType = groupType) }
            val groupCommentsDeferred = async { commentRepository.getGroupComments(isPublic = true, groupId = groupId, groupType = groupType) }

            val groupInfoResult = groupInfoDeferred.await()
            val groupHostResult = groupHostDeferred.await()
            val groupCommentsResult = groupCommentsDeferred.await()

            if (groupInfoResult.isSuccess && groupHostResult.isSuccess && groupCommentsResult.isSuccess) {
                Result.success(
                    GroupDetail(
                        groupInfo = groupInfoResult.getOrThrow(),
                        groupHost = groupHostResult.getOrThrow(),
                        groupComments = groupCommentsResult.getOrThrow()
                    )
                )
            } else {
                Result.failure(
                    Exception(
                        "Failed to load group detail: " +
                            "GroupInfo=${groupInfoResult.exceptionOrNull()?.message}, " +
                            "GroupHost=${groupHostResult.exceptionOrNull()?.message}, " +
                            "GroupComments=${groupCommentsResult.exceptionOrNull()?.message}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
