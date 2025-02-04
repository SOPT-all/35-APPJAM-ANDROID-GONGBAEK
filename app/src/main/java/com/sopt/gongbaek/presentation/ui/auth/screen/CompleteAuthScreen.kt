package com.sopt.gongbaek.presentation.ui.auth.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun CompleteAuthRoute(
    viewModel: AuthViewModel,
    navigateHome: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    var backPressedTime by remember { mutableLongStateOf(0L) }
    val backPressThreshold = 2000
    val context = LocalContext.current

    BackHandler {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - backPressedTime <= backPressThreshold) {
            (context as? Activity)?.finish()
        } else {
            backPressedTime = currentTimeMillis
        }
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                if (sideEffect is AuthContract.SideEffect.NavigateHome) {
                    navigateHome()
                }
            }
    }
    CompleteAuthScreen(
        navigateHome = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateHome) }
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
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "가입이 완료되었어요!",
                color = GongBaekTheme.colors.black,
                style = GongBaekTheme.typography.head2.b24,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 99.dp, bottom = 8.dp)
            )

            Text(
                text = buildAnnotatedString {
                    append("0을 100으로 채우러 가볼까요?")

                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.mainOrange
                        ),
                        start = 0,
                        end = 1
                    )

                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.mainOrange
                        ),
                        start = 3,
                        end = 6
                    )
                },
                color = GongBaekTheme.colors.black,
                style = GongBaekTheme.typography.head2.b24,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Image(
            painter = painterResource(id = R.drawable.img_auth_congratulation),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 38.dp)
                .width((LocalConfiguration.current.screenWidthDp * 0.9).dp)
                .aspectRatio(328f / 343f)
        )

        GongBaekBasicButton(
            title = "공백 채우러 가기",
            enabled = true,
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
