package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun CompleteAuthRoute(
    viewModel: AuthViewModel,
    navigateHome: () -> Unit,
) {
    CompleteAuthScreen(
        navigateHome = navigateHome
    )
}

@Composable
private fun CompleteAuthScreen(
    navigateHome: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        PageDescriptionSection(
            titleResId = R.string.auth_complete_title,
            modifier = Modifier
                .padding(top = (LocalConfiguration.current.screenHeightDp * 0.2f).dp)
                .align(Alignment.TopCenter)
        )

        GongBaekBasicButton(
            title = "공백 채우러 가기",
            onClick = navigateHome,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCompleteAuthScreen() {
    GONGBAEKTheme {
        CompleteAuthScreen(
            navigateHome = {}
        )
    }
}
