package com.sopt.gongbaek.presentation.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sopt.gongbaek.R
import com.sopt.gongbaek.R.drawable
import com.sopt.gongbaek.presentation.model.NavigationRoute
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    val backgroundColor = GongBaekTheme.colors.black
    val defaultBackgroundColor = GongBaekTheme.colors.white
    var isContentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            systemUiController.setSystemBarsColor(color = backgroundColor)
            delay(1000L)
            isContentVisible = true
            delay(1000L)
            systemUiController.setSystemBarsColor(color = defaultBackgroundColor)
            navController.navigate(NavigationRoute.OnboardingNavGraph.ONBOARDING_NAV_GRAPH) {
                popUpTo(NavigationRoute.SplashRoute.SPLASH) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GongBaekTheme.colors.black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isContentVisible,
            enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(
                initialOffsetY = { -it }
            )
        ) {
            Image(
                painter = painterResource(R.drawable.img_logo),
                contentDescription = null,
                modifier = Modifier.padding(top = (LocalConfiguration.current.screenHeightDp * 0.38).dp)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        AnimatedVisibility(
            visible = isContentVisible,
            enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(
                initialOffsetY = { it }
            )
        ) {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.splash_title))
                    addStyle(
                        style = SpanStyle(color = GongBaekTheme.colors.mainOrange),
                        start = 0,
                        end = 1
                    )
                    addStyle(
                        style = SpanStyle(color = GongBaekTheme.colors.gray02),
                        start = 1,
                        end = 3
                    )
                    addStyle(
                        style = SpanStyle(color = GongBaekTheme.colors.mainOrange),
                        start = 4,
                        end = 5
                    )
                    addStyle(
                        style = SpanStyle(color = GongBaekTheme.colors.gray02),
                        start = 5,
                        end = 11
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSplashScreen() {
    GONGBAEKTheme {
        SplashScreen(
            navController = rememberNavController()
        )
    }
}
