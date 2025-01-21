package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.util.GongBaekLottieAnimation
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun TimetableConvertRoute(
    viewModel: AuthViewModel,
    navigateGapTimeTable: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                if (sideEffect is AuthContract.SideEffect.NavigateGapTimetable) {
                    navigateGapTimeTable()
                }
            }
    }

    TimetableConvertScreen(
        onComplete = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateGapTimetable)}
    )
}

@Composable
private fun TimetableConvertScreen(
    onComplete: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GongBaekTheme.colors.white)
            .padding(end = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        GongBaekLottieAnimation(
            lottieJson = R.raw.lottie_timetable_convert,
            onComplete = onComplete,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun PreviewTimetableScreen() {
    TimetableConvertScreen(
        onComplete = {}
    )
}
