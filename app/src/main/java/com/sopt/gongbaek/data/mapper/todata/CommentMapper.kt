package com.sopt.gongbaek.data.mapper.todata

import com.sopt.gongbaek.data.remote.dto.request.PostCommentRequestDto
import com.sopt.gongbaek.domain.model.Comment

fun Comment.toData() = PostCommentRequestDto(
    groupId = groupId,
    groupType = cycle,
    isPublic = isPublic,
    body = content
)