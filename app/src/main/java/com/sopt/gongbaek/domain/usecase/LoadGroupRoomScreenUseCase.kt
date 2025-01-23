package com.sopt.gongbaek.domain.usecase

import com.sopt.gongbaek.domain.model.GroupRoom
import com.sopt.gongbaek.domain.repository.CommentRepository
import com.sopt.gongbaek.domain.repository.GroupRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class LoadGroupRoomScreenUseCase(
    private val groupRepository: GroupRepository,
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(groupId: Int, groupType: String): Result<GroupRoom> = coroutineScope {
        try {
            val groupInfoDeferred = async { groupRepository.getGroupDetail(groupId = groupId, groupType = groupType) }
            val groupMembersDeferred = async { groupRepository.getGroupMembers(groupId = groupId, groupType = groupType) }
            val groupCommentsDeferred = async { commentRepository.getGroupComments(isPublic = false, groupId = groupId, groupType = groupType) }

            val groupInfoResult = groupInfoDeferred.await()
            val groupMembersResult = groupMembersDeferred.await()
            val groupCommentsResult = groupCommentsDeferred.await()

            if (groupInfoResult.isSuccess && groupMembersResult.isSuccess && groupCommentsResult.isSuccess) {
                Result.success(
                    GroupRoom(
                        groupInfo = groupInfoResult.getOrThrow(),
                        groupMembers = groupMembersResult.getOrThrow(),
                        groupComments = groupCommentsResult.getOrThrow()
                    )
                )
            } else {
                Result.failure(
                    Exception(
                        "Failed to load group detail: " +
                            "GroupInfo=${groupInfoResult.exceptionOrNull()?.message}, " +
                            "GroupMembers=${groupMembersResult.exceptionOrNull()?.message}, " +
                            "GroupComments=${groupCommentsResult.exceptionOrNull()?.message}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
