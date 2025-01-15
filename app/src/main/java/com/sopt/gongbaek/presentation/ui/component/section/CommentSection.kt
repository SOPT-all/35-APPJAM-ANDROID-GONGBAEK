package com.sopt.gongbaek.presentation.ui.component.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.domain.model.Comment
import com.sopt.gongbaek.domain.model.GroupComment
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.presentation.util.extension.roundedBackgroundWithBorder
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun CommentSection(
    groupComment: GroupComment,
    value: String,
    onValueChanged: (String) -> Unit,
    onRefreshClicked: () -> Unit = {},
    onDeleteClicked: () -> Unit = {},
    onSendClicked: () -> Unit = {}
) {
    Column {
        CommentSectionHeader(
            commentCount = groupComment.commentCount,
            onRefreshClicked = onRefreshClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 10.dp)
                .padding(vertical = 16.dp),
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(items = groupComment.commentList) { comment ->
                CommentSectionItem(
                    comment = comment,
                    onDeleteClicked = onDeleteClicked,
                )
            }
        }

        CommentSectionTextField(
            value = value,
            onValueChanged = onValueChanged,
            onSendClicked = onSendClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}

@Composable
private fun CommentSectionHeader(
    commentCount: Int,
    onRefreshClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.comment_section_header, commentCount),
            color = GongBaekTheme.colors.gray09,
            style = GongBaekTheme.typography.body1.sb16
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_refresh_32),
            contentDescription = null,
            modifier = Modifier.clickableWithoutRipple(onClick = onRefreshClicked)
        )
    }
}

@Composable
private fun CommentSectionItem(
    comment: Comment,
    onDeleteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(top = 14.dp)
        .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = comment.commentWriter,
                color = GongBaekTheme.colors.gray10,
                style = GongBaekTheme.typography.body1.sb16
            )
            if (comment.isGroupHost) {
                Box(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .roundedBackgroundWithBorder(
                            cornerRadius = 4.dp,
                            backgroundColor = GongBaekTheme.colors.gray01
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.comment_section_comment_writer_chip),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp),
                        color = GongBaekTheme.colors.mainOrange,
                        style = GongBaekTheme.typography.caption2.m12
                    )
                }
            }
            if (comment.isWriter) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_comment_x_20),
                    contentDescription = null,
                    modifier = Modifier.clickableWithoutRipple(onClick = onDeleteClicked)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = comment.commentContent,
            color = GongBaekTheme.colors.gray08,
            style = GongBaekTheme.typography.body2.r14
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${comment.getCreatedMonth()}/${comment.getCreatedDay()}/${comment.getCreatedHour()}:${comment.getCreatedMinute()}",
            color = GongBaekTheme.colors.gray05,
            style = GongBaekTheme.typography.caption2.r12
        )
        Spacer(modifier = Modifier.height(14.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = GongBaekTheme.colors.gray02
        )
    }
}

@Composable
private fun CommentSectionTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    onSendClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 6.dp,
                backgroundColor = GongBaekTheme.colors.gray01
            ),
        maxLines = 3,
        cursorBrush = SolidColor(GongBaekTheme.colors.gray05),
    ) { innerTextField ->
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = "댓글을 작성해주세요.",
                        color = GongBaekTheme.colors.gray04,
                        style = GongBaekTheme.typography.body1.m16
                    )
                }
                innerTextField()
            }
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_send_24),
                contentDescription = null,
                modifier = Modifier.clickableWithoutRipple(onClick = onSendClicked)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentSectionPreview() {
    GONGBAEKTheme {
        var value by remember { mutableStateOf("") }
        val onValueChanged: (String) -> Unit = { newValue -> value = newValue }
        CommentSection(
            groupComment = GroupComment(
                commentCount = 6,
                commentList = listOf(
                    Comment(
                        groupId = 1,
                        groupType = "ONCE",
                        commentId = 1,
                        commentWriter = "파이리",
                        commentContent = "어디서 만나는거임?",
                        createdAt = "2023-12-27-02-18",
                        isGroupHost = false,
                        isWriter = false
                    ),
                    Comment(
                        groupId = 1,
                        groupType = "ONCE",
                        commentId = 1,
                        commentWriter = "로이임탄",
                        commentContent = "음 아직 안정하긴 했는데 아마 학교 주변 1km 이내일 것 같아요!",
                        createdAt = "2023-12-27-02-19",
                        isGroupHost = true,
                        isWriter = true
                    ),
                    Comment(
                        groupId = 1,
                        groupType = "ONCE",
                        commentId = 1,
                        commentWriter = "훈발놈",
                        commentContent = "저도 아싸라서 친구가 없어요...",
                        createdAt = "2023-12-27-02-24",
                        isGroupHost = false,
                        isWriter = false
                    )
                )
            ),
            value = value,
            onValueChanged = onValueChanged
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentSectionHeaderPreview() {
    GONGBAEKTheme {
        CommentSectionHeader(
            commentCount = 3,
            onRefreshClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentSectionItemPreview() {
    GONGBAEKTheme {
        CommentSectionItem(
            comment = Comment(
                groupId = 1,
                groupType = "ONCE",
                commentId = 1,
                commentWriter = "로이임탄",
                commentContent = "음 아직 안정하긴 했는데 아마 학교 주변 1km 이내일 것 같아요!",
                createdAt = "2023-12-27-19-20",
                isGroupHost = true,
                isWriter = true
            ),
            onDeleteClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentTextFieldPreview() {
    GONGBAEKTheme {
        var value by remember { mutableStateOf("") }
        val onValueChanged: (String) -> Unit = { newValue -> value = newValue }
        CommentSectionTextField(
            value = value,
            onValueChanged = onValueChanged,
            onSendClicked = {}
        )
    }
}
