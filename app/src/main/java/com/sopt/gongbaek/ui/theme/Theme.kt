package com.sopt.gongbaek.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object GongBaekTheme {
    val colors: GongBaekColors
        @Composable
        @ReadOnlyComposable
        get() = LocalGongBaekColors.current

    val typography: GongBaekTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalGongBaekTypography.current
}

@Composable
fun ProvideGongBaekColorsAndTypography(
    colors: GongBaekColors,
    typography: GongBaekTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalGongBaekColors provides colors,
        LocalGongBaekTypography provides typography,
        content = content
    )
}

@Composable
fun GONGBAEKTheme(
    content: @Composable () -> Unit
) {
    ProvideGongBaekColorsAndTypography(
        colors = defaultGongBaekColors,
        typography = defaultGongBaekTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    statusBarColor = white.toArgb()
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = true
                }
            }
        }
    }

    MaterialTheme(
        content = content
    )
}
