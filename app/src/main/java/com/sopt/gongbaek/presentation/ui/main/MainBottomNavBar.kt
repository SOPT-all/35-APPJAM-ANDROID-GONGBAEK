package com.sopt.gongbaek.presentation.ui.main

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.presentation.type.MainBottomNavBarTabType
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun MainBottomNavBar(
    isVisible: Boolean,
    bottomNavBarTabTypes: List<MainBottomNavBarTabType>,
    currentBottomNavBarTab: MainBottomNavBarTabType?,
    onBottomNavBarTabSelected: (MainBottomNavBarTabType) -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    if (isVisible) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = GongBaekTheme.colors.white),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomNavBarTabTypes.forEach { bottomNavBarTabType ->
                MainBottomNavBarItem(
                    context = context,
                    bottomNavBarTabType = bottomNavBarTabType,
                    isSelected = currentBottomNavBarTab == bottomNavBarTabType,
                    onClick = { onBottomNavBarTabSelected(bottomNavBarTabType) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun MainBottomNavBarItem(
    context: Context,
    bottomNavBarTabType: MainBottomNavBarTabType,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickableWithoutRipple(onClick = onClick)
            .padding(vertical = 14.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = ImageVector.vectorResource(
                id = if (isSelected) {
                    bottomNavBarTabType.selectedIconRes
                } else {
                    bottomNavBarTabType.unselectedIconRes
                }
            ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = context.getString(bottomNavBarTabType.label),
            style = GongBaekTheme.typography.body2.m14,
            color = if (isSelected) GongBaekTheme.colors.gray10 else GongBaekTheme.colors.gray05
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBottomNavBarPreview() {
    GONGBAEKTheme {
        MainBottomNavBar(
            isVisible = true,
            bottomNavBarTabTypes = MainBottomNavBarTabType.entries.toList(),
            currentBottomNavBarTab = MainBottomNavBarTabType.HOME,
            onBottomNavBarTabSelected = {}
        )
    }
}
