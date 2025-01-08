package com.sopt.gongbaek.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.sopt.gongbaek.R

object PretendardFont {
    val Bold = FontFamily(Font(R.font.pretendard_bold))
    val SemiBold = FontFamily(Font(R.font.pretendard_semibold))
    val Medium = FontFamily(Font(R.font.pretendard_medium))
    val Regular = FontFamily(Font(R.font.pretendard_regular))
}

sealed interface TypographyTokens {
    @Immutable
    data class Head1(
        val b30: TextStyle
    )

    @Immutable
    data class Head2(
        val b24: TextStyle,
        val m24: TextStyle,
        val r24: TextStyle
    )

    @Immutable
    data class Title1(
        val b20: TextStyle,
        val m20: TextStyle,
        val r20: TextStyle
    )

    @Immutable
    data class Title2(
        val b18: TextStyle,
        val sb18: TextStyle,
        val m18: TextStyle,
        val r18: TextStyle
    )

    @Immutable
    data class Body1(
        val b16: TextStyle,
        val sb16: TextStyle,
        val m16: TextStyle,
        val r16: TextStyle
    )

    @Immutable
    data class Body2(
        val b14: TextStyle,
        val sb14: TextStyle,
        val m14: TextStyle,
        val r14: TextStyle
    )

    @Immutable
    data class Caption1(
        val sb13: TextStyle,
        val m13: TextStyle,
        val r13: TextStyle
    )

    @Immutable
    data class Caption2(
        val b12: TextStyle,
        val m12: TextStyle,
        val r12: TextStyle
    )
}

@Immutable
data class GongBaekTypography(
    val head1: TypographyTokens.Head1,
    val head2: TypographyTokens.Head2,
    val title1: TypographyTokens.Title1,
    val title2: TypographyTokens.Title2,
    val body1: TypographyTokens.Body1,
    val body2: TypographyTokens.Body2,
    val caption1: TypographyTokens.Caption1,
    val caption2: TypographyTokens.Caption2
)

val defaultGongBaekTypography = GongBaekTypography(
    head1 = TypographyTokens.Head1(
        b30 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 30.sp,
            lineHeight = 30.sp,
            letterSpacing = (-1).sp
        )
    ),
    head2 = TypographyTokens.Head2(
        b24 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 24.sp,
            lineHeight = 26.sp,
            letterSpacing = (-1).sp
        ),
        m24 = TextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 24.sp,
            lineHeight = 26.sp,
            letterSpacing = (-1).sp
        ),
        r24 = TextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 24.sp,
            lineHeight = 26.sp,
            letterSpacing = (-1).sp
        )
    ),
    title1 = TypographyTokens.Title1(
        b20 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            letterSpacing = (-1).sp
        ),
        m20 = TextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            letterSpacing = (-1).sp
        ),
        r20 = TextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            letterSpacing = (-1).sp
        )
    ),
    title2 = TypographyTokens.Title2(
        b18 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.5).sp
        ),
        sb18 = TextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.5).sp
        ),
        m18 = TextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.5).sp
        ),
        r18 = TextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.5).sp
        )
    ),
    body1 = TypographyTokens.Body1(
        b16 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.5).sp
        ),
        sb16 = TextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.5).sp
        ),
        m16 = TextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.5).sp
        ),
        r16 = TextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.5).sp
        )
    ),
    body2 = TypographyTokens.Body2(
        b14 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.5).sp
        ),
        sb14 = TextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.5).sp
        ),
        m14 = TextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.5).sp
        ),
        r14 = TextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.5).sp
        )
    ),
    caption1 = TypographyTokens.Caption1(
        sb13 = TextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.5).sp
        ),
        m13 = TextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.5).sp
        ),
        r13 = TextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.5).sp
        )
    ),
    caption2 = TypographyTokens.Caption2(
        b12 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.5).sp
        ),
        m12 = TextStyle(
            fontFamily = PretendardFont.Medium,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.5).sp
        ),
        r12 = TextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.5).sp
        )
    )
)

val LocalGongBaekTypography = staticCompositionLocalOf { defaultGongBaekTypography }
