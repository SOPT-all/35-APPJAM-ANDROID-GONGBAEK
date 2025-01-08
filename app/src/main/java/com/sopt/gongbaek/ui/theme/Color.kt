package com.sopt.gongbaek.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// GongBaek Brand Colors
val mainOrange = Color(color = 0xFFFE7435)
val subOrange = Color(color = 0xFFFEEDE3)
val subYellow = Color(color = 0xFFFFC800)
val subGreen = Color(color = 0xFF04BA70)
val subBlue = Color(color = 0xFF2C599D)

// System Colors
val errorRed = Color(color = 0xFFFF5454)

// Grayscale
val white = Color(color = 0xFFFFFFFF)
val gray01 = Color(color = 0xFFF5F5F5)
val gray02 = Color(color = 0xFFEBEBEB)
val gray03 = Color(color = 0xFFDDDDDD)
val gray04 = Color(color = 0xFFC2C2C2)
val gray05 = Color(color = 0xFFAEAEAE)
val gray06 = Color(color = 0xFF848484)
val gray07 = Color(color = 0xFF6D6D6D)
val gray08 = Color(color = 0xFF4B4B4B)
val gray09 = Color(color = 0xFF2F2F2F)
val gray10 = Color(color = 0xFF151515)
val black = Color(color = 0xFF000000)

@Immutable
data class GongBaekColors(
    val mainOrange: Color,
    val subOrange: Color,
    val subYellow: Color,
    val subGreen: Color,
    val subBlue: Color,

    val errorRed: Color,

    val white: Color,
    val gray01: Color,
    val gray02: Color,
    val gray03: Color,
    val gray04: Color,
    val gray05: Color,
    val gray06: Color,
    val gray07: Color,
    val gray08: Color,
    val gray09: Color,
    val gray10: Color,
    val black: Color
)

val defaultGongBaekColors =
    GongBaekColors(
        mainOrange = mainOrange,
        subOrange = subOrange,
        subYellow = subYellow,
        subGreen = subGreen,
        subBlue = subBlue,

        errorRed = errorRed,

        white = white,
        gray01 = gray01,
        gray02 = gray02,
        gray03 = gray03,
        gray04 = gray04,
        gray05 = gray05,
        gray06 = gray06,
        gray07 = gray07,
        gray08 = gray08,
        gray09 = gray09,
        gray10 = gray10,
        black = black
    )

val LocalGongBaekColors = staticCompositionLocalOf { defaultGongBaekColors }
