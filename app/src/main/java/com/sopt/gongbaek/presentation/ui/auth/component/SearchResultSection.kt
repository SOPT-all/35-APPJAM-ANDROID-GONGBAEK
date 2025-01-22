package com.sopt.gongbaek.presentation.ui.auth.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun SearchResultSection(
    univSearchResult: List<String>,
    modifier: Modifier = Modifier,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(univSearchResult) { university ->
            SearchResultItem(
                university = university,
                isSelected = selectedItem == university,
                onSelectionChange = { isSelected ->
                    if (isSelected) {
                        onItemSelected(university)
                    } else {
                        onItemSelected("")
                    }
                }
            )
        }
    }
}

@Composable
private fun SearchResultItem(
    university: String,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isSelected) GongBaekTheme.colors.subOrange else GongBaekTheme.colors.white
            )
            .clickableWithoutRipple { onSelectionChange(!isSelected) }
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = university,
            color = if (isSelected) GongBaekTheme.colors.mainOrange else GongBaekTheme.colors.gray08,
            style = GongBaekTheme.typography.body1.r16,
            modifier = Modifier.padding(vertical = 17.dp)
        )

        if (isSelected) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_check_orange_24),
                contentDescription = null,
                tint = GongBaekTheme.colors.mainOrange
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSearchResultSection() {
    val universities = listOf(
        "건국대학교 서울캠퍼스",
        "건국대학교 서울캠퍼스",
        "건국대학교 서울캠퍼스"
    )
    SearchResultSection(
        univSearchResult = universities,
        onItemSelected = {},
        selectedItem = ""
    )
}
