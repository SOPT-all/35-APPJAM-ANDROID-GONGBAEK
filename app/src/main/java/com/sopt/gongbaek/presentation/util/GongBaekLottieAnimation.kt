package com.sopt.gongbaek.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sopt.gongbaek.R
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import kotlinx.coroutines.delay

@Composable
fun GongBaekLottieAnimation(
    lottieJson: Int,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier,
    completionDelay: Long = 0,
    maxFrame: Int = 800
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieJson))
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(lottieComposition) {
        lottieComposition?.let {
            lottieAnimatable.animate(
                composition = it,
                clipSpec = LottieClipSpec.Frame(0, maxFrame)
            )
            delay(completionDelay)
            onComplete()
        }
    }

    LottieAnimation(
        composition = lottieComposition,
        progress = { lottieAnimatable.progress },
        modifier = modifier,
        clipToCompositionBounds = false
    )

    DisposableEffect(Unit) {
        onDispose {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GongBaekLottieAnimationPreview() {
    GONGBAEKTheme {
        GongBaekLottieAnimation(
            lottieJson = R.raw.lottie_splash,
            completionDelay = 3000L,
            onComplete = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
