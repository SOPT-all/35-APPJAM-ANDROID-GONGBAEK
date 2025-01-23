package com.sopt.gongbaek.data.mapper.todomain

import com.sopt.gongbaek.data.remote.dto.response.GroupCommentsResponseDto
import com.sopt.gongbaek.domain.model.GroupComments

fun GroupCommentsResponseDto.toDomain() = GroupComments(
    groupId = groupId,
    groupStatus = groupStatus,
    groupCycle = groupType,
    commentCount = commentCount,
    groupCommentList = comments.map { comment ->
        GroupComments.GroupComment(
            commentId = comment.commentId,
            commentWriter = comment.nickname,
            commentContent = comment.body,
            createdAt = comment.createdAt,
            isGroupHost = comment.isGroupHost,
            isWriter = comment.isWriter
        )
    }
)