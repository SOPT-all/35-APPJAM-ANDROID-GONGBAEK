package com.sopt.gongbaek.presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.presentation.util.GongBaekLottieAnimation
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    val systemUiController = rememberSystemUiController()
    val backgroundColor = GongBaekTheme.colors.gray10
    val defaultBackgroundColor = GongBaekTheme.colors.white
    val onComplete = {
        navController.navigate(NavigationRoute.OnboardingNavGraph.ONBOARDING_NAV_GRAPH) {
            popUpTo(NavigationRoute.SplashRoute.SPLASH) { inclusive = true }
        }
    }

    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(color = backgroundColor)
    }

    DisposableEffect(Unit) {
        onDispose {
            systemUiController.setSystemBarsColor(color = defaultBackgroundColor)
        }
    }

    Box(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.gray10)
            .padding(end = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        GongBaekLottieAnimation(
            lottieJson = R.raw.lottie_splash,
            onComplete = onComplete,
            modifier = Modifier.fillMaxSize(),
            maxFrame = 120
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    GONGBAEKTheme {
        SplashScreen(
            navController = rememberNavController()
        )
    }
}
