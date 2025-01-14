package com.sopt.gongbaek.presentation.ui.component.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    groupComment: GroupComment
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CommentSectionHeader(commentCount = groupComment.commentCount)

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = groupComment.commentList) { comment ->
                CommentSectionItem(comment = comment)
            }
        }
    }
}


@Composable
private fun CommentSectionHeader(
    commentCount: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 10.dp)
            .padding(vertical = 16.dp),
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
            contentDescription = null
        )
    }
}

@Composable
private fun CommentSectionItem(
    comment: Comment,
    onCommentDeleteClicked: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
                            backgroundColor = GongBaekTheme.colors.gray01,
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "모집자",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp),
                        color = GongBaekTheme.colors.mainOrange,
                        style = GongBaekTheme.typography.caption2.m12
                    )
                }
            }
            if (comment.isWriter) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic__comment_x_20),
                    contentDescription = null,
                    modifier = Modifier.clickableWithoutRipple(onClick = onCommentDeleteClicked)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = comment.commentContent,
            color = GongBaekTheme.colors.gray08,
            style = GongBaekTheme.typography.body2.r14,
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
private fun CommentTextField() {

}

@Preview(showBackground = true)
@Composable
private fun CommentSectionHeaderPreview() {
    GONGBAEKTheme {
        CommentSectionHeader(
            commentCount = 3
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
            onCommentDeleteClicked = {}
        )
    }
}