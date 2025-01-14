package com.sopt.gongbaek.presentation.ui.component.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun CommentSection(

) {

}

@Composable
private fun CommentSectionHeader(
    commentCount: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 8.dp)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "댓글 ${commentCount}개",
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

@Preview(showBackground = true)
@Composable
private fun CommentHSectionHeaderPreview() {
    GONGBAEKTheme {
        CommentSectionHeader(
            commentCount = 3
        )
    }
}