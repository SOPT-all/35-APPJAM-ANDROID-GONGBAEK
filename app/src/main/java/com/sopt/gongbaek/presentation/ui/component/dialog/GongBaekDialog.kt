package com.sopt.gongbaek.presentation.ui.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.type.GongBaekDialogType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun GongBaekDialog(
    gongBaekDialogType: GongBaekDialogType,
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDismissButtonClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(color = GongBaekTheme.colors.white, shape = RoundedCornerShape(20.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(gongBaekDialogType.imageResId),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 14.dp, bottom = 20.dp)
                .padding(horizontal = 36.dp)
                .aspectRatio(1f / 1f)
        )

        Text(
            text = stringResource(gongBaekDialogType.titleResId),
            color = GongBaekTheme.colors.gray10,
            style = GongBaekTheme.typography.title2.sb18
        )

        if (gongBaekDialogType.descriptionResId != null) {
            Text(
                text = stringResource(gongBaekDialogType.descriptionResId),
                color = GongBaekTheme.colors.gray07,
                style = GongBaekTheme.typography.body2.m14,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )
        } else {
            Spacer(Modifier.height(20.dp))
        }

        when (gongBaekDialogType) {
            GongBaekDialogType.ENTER_SUCCESS ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    GongBaekBasicButton(
                        title = stringResource(R.string.dialog_button_close),
                        onClick = onDismissButtonClick,
                        verticalPadding = 12,
                        modifier = Modifier.weight(1f),
                        enableButtonColor = GongBaekTheme.colors.gray09
                    )
                    GongBaekBasicButton(
                        title = stringResource(R.string.dialog_button_group_room),
                        onClick = onConfirmButtonClick,
                        verticalPadding = 12,
                        modifier = Modifier.weight(2f)
                    )
                }

            else ->
                GongBaekBasicButton(
                    title = stringResource(R.string.dialog_button_confirm),
                    onClick = onConfirmButtonClick
                )
        }
    }
}

@Preview
@Composable
private fun PreviewGongBaekDialog1() {
    Box(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
            .fillMaxSize()
    ) {
        Dialog(
            onDismissRequest = { }
        ) {
            GongBaekDialog(
                gongBaekDialogType = GongBaekDialogType.REGISTER_FAIL,
                onConfirmButtonClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewGongBaekDialog2() {
    Box(
        modifier = Modifier
            .background(color = GongBaekTheme.colors.white)
            .fillMaxSize()
    ) {
        Dialog(
            onDismissRequest = { }
        ) {
            GongBaekDialog(
                gongBaekDialogType = GongBaekDialogType.ENTER_SUCCESS,
                onConfirmButtonClick = {},
                onDismissButtonClick = {}
            )
        }
    }
}
