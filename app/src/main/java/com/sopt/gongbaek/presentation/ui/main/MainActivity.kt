package com.sopt.gongbaek.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GONGBAEKTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Column {
        val textStyles = listOf(
            GongBaekTheme.typography.head1.b30 to "head1.b30",
            GongBaekTheme.typography.head2.b24 to "head2.b24",
            GongBaekTheme.typography.head2.m24 to "head2.m24",
            GongBaekTheme.typography.head2.r24 to "head2.r24",

            GongBaekTheme.typography.title1.b20 to "title1.b20",
            GongBaekTheme.typography.title1.m20 to "title1.m20",
            GongBaekTheme.typography.title1.r20 to "title1.r20",

            GongBaekTheme.typography.title2.b18 to "title2.b18",
            GongBaekTheme.typography.title2.sb18 to "title2.sb18",
            GongBaekTheme.typography.title2.m18 to "title2.m18",
            GongBaekTheme.typography.title2.r18 to "title2.r18",

            GongBaekTheme.typography.body1.b16 to "body1.b16",
            GongBaekTheme.typography.body1.sb16 to "body1.sb16",
            GongBaekTheme.typography.body1.m16 to "body1.m16",
            GongBaekTheme.typography.body1.r16 to "body1.r16",

            GongBaekTheme.typography.body2.b14 to "body2.b14",
            GongBaekTheme.typography.body2.sb14 to "body2.sb14",
            GongBaekTheme.typography.body2.m14 to "body2.m14",
            GongBaekTheme.typography.body2.r14 to "body2.r14",

            GongBaekTheme.typography.caption1.sb13 to "caption1.sb13",
            GongBaekTheme.typography.caption1.m13 to "caption1.m13",
            GongBaekTheme.typography.caption1.r13 to "caption1.r13",

            GongBaekTheme.typography.caption2.b12 to "caption2.b12",
            GongBaekTheme.typography.caption2.m12 to "caption2.m12",
            GongBaekTheme.typography.caption2.r12 to "caption2.r12"
        )

        textStyles.forEach { (style, label) ->
            Text(
                text = "Hello 안녕 -> $label",
                style = style
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GongBaekColorsPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        listOf(
            "Main Orange" to GongBaekTheme.colors.mainOrange,
            "Sub Orange" to GongBaekTheme.colors.subOrange,
            "Sub Yellow" to GongBaekTheme.colors.subYellow,
            "Sub Green" to GongBaekTheme.colors.subGreen,
            "Sub Blue" to GongBaekTheme.colors.subBlue,
            "Error Red" to GongBaekTheme.colors.errorRed,
            "White" to GongBaekTheme.colors.white,
            "Gray01" to GongBaekTheme.colors.gray01,
            "Gray02" to GongBaekTheme.colors.gray02,
            "Gray03" to GongBaekTheme.colors.gray03,
            "Gray04" to GongBaekTheme.colors.gray04,
            "Gray05" to GongBaekTheme.colors.gray05,
            "Gray06" to GongBaekTheme.colors.gray06,
            "Gray07" to GongBaekTheme.colors.gray07,
            "Gray08" to GongBaekTheme.colors.gray08,
            "Gray09" to GongBaekTheme.colors.gray09,
            "Gray10" to GongBaekTheme.colors.gray10,
            "Black" to GongBaekTheme.colors.black
        ).forEach { (label, color) ->
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(color)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = label)
            }
        }
    }
}
