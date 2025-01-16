package com.sopt.gongbaek.presentation.ui.component.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GroupPeopleDescription(
    description: String,
    textColor: Color,
    textStyle: TextStyle
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_people_16),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = description,
            color = textColor,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = textStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GroupPeopleDescriptionPreview() {
    GONGBAEKTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            GroupPeopleDescription(
                description = "4명",
                textColor = GongBaekTheme.colors.gray08,
                textStyle = GongBaekTheme.typography.body1.m16
            )
        }
    }
}
