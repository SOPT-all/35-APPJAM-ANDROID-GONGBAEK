package com.sopt.gongbaek.presentation.ui.component.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun PageDescriptionSection(
    titleResId: Int,
    modifier: Modifier = Modifier,
    descriptionResId: Int? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(titleResId),
            color = GongBaekTheme.colors.gray10,
            style = GongBaekTheme.typography.head2.b24
        )

        if (descriptionResId != null) {
            Text(
                text = stringResource(descriptionResId),
                color = GongBaekTheme.colors.gray07,
                style = GongBaekTheme.typography.body1.m16,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDescriptionSection1() {
    Column(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
            .padding(20.dp)
            .fillMaxSize()
    ) {
        PageDescriptionSection(
            titleResId = R.string.group_place_people_title,
            descriptionResId = R.string.dialog_description_enter_fail
        )
    }
}

@Preview
@Composable
private fun PreviewDescriptionSection2() {
    Column(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
            .padding(20.dp)
            .fillMaxSize()
    ) {
        PageDescriptionSection(
            titleResId = R.string.group_place_people_title
        )
    }
}
