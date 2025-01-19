package com.sopt.gongbaek.presentation.ui.groupdetail.screen

import androidx.compose.runtime.Composable
import com.sopt.gongbaek.domain.model.GroupComment
import com.sopt.gongbaek.presentation.ui.component.section.CommentSection

@Composable
fun GroupDetailCommentScreen(
    groupComment: GroupComment,
    value: String,
    onValueChanged: (String) -> Unit,
    onRefreshClicked: () -> Unit = {},
    onDeleteClicked: () -> Unit = {},
    onSendClicked: () -> Unit = {}
) {
    CommentSection(
        groupComment = groupComment,
        value = value,
        onValueChanged = onValueChanged,
        onRefreshClicked = onRefreshClicked,
        onDeleteClicked = onDeleteClicked,
        onSendClicked = onSendClicked
    )
}
