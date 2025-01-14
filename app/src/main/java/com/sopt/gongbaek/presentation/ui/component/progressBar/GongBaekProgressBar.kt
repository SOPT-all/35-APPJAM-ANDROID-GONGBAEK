package com.sopt.gongbaek.presentation.ui.component.progressBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GongBaekProgressBar(
    progressPercent: Float,
    modifier: Modifier = Modifier
) {
    LinearProgressIndicator(
        progress = { progressPercent },
        modifier = modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.009f),
        color = GongBaekTheme.colors.gray10,
        trackColor = GongBaekTheme.colors.gray02,
        strokeCap = StrokeCap.Square
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewGongBeakProgressBar() {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        GongBaekProgressBar(progressPercent = 0.1f)
        GongBaekProgressBar(progressPercent = 0.2f)
        GongBaekProgressBar(progressPercent = 0.3f)
        GongBaekProgressBar(progressPercent = 0.4f)
        GongBaekProgressBar(progressPercent = 0.5f)
        GongBaekProgressBar(progressPercent = 0.6f)
        GongBaekProgressBar(progressPercent = 0.7f)
        GongBaekProgressBar(progressPercent = 0.8f)
        GongBaekProgressBar(progressPercent = 0.9f)
        GongBaekProgressBar(progressPercent = 1f)
    }
}
