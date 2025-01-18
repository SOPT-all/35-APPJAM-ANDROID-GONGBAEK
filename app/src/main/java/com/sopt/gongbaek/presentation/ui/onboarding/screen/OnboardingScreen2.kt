package com.sopt.gongbaek.presentation.ui.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import com.sopt.gongbaek.ui.theme.defaultGongBaekTypography

@Composable
fun OnboardingScreen2(
    onBackClick: () -> Unit
) {
    val highlightedTextStyle = defaultGongBaekTypography.body1.sb16
    val basicTextStyle = defaultGongBaekTypography.body1.m16

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StartTitleTopBar(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBackClick
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.onboarding_2_title),
                modifier = Modifier.padding(top = (LocalConfiguration.current.screenHeightDp * 0.07).dp),
                color = GongBaekTheme.colors.black,
                style = GongBaekTheme.typography.head2.b24
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = buildAnnotatedString {
                    append(
                        stringResource(R.string.onboarding_2_description_1) +
                            stringResource(R.string.onboarding_2_description_2)
                    )

                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.gray07,
                            fontSize = basicTextStyle.fontSize,
                            fontWeight = basicTextStyle.fontWeight,
                            fontFamily = basicTextStyle.fontFamily
                        ),
                        start = 0,
                        end = 21
                    )

                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.mainOrange,
                            fontSize = highlightedTextStyle.fontSize,
                            fontWeight = highlightedTextStyle.fontWeight,
                            fontFamily = highlightedTextStyle.fontFamily
                        ),
                        start = 21,
                        end = 36
                    )

                    addStyle(
                        style = SpanStyle(
                            color = GongBaekTheme.colors.gray07,
                            fontSize = basicTextStyle.fontSize,
                            fontWeight = basicTextStyle.fontWeight,
                            fontFamily = basicTextStyle.fontFamily
                        ),
                        start = 36,
                        end = 47
                    )
                }
            )
            Spacer(modifier = Modifier.height(54.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreen2Preview() {
    GONGBAEKTheme {
        OnboardingScreen2(
            onBackClick = {}
        )
    }
}
