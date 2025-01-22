package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.type.GongBaekBasicTextFieldType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.textfield.GongBaekBasicTextField
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.presentation.util.extension.isKoreanChar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme

@Composable
fun NicknameRoute(
    viewModel: AuthViewModel,
    navigateUnivMajor: () -> Unit,
    navigateBack: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                if (sideEffect is AuthContract.SideEffect.NavigateBack) {
                    navigateBack()
                }
                if (sideEffect is AuthContract.SideEffect.NavigateUnivMajor) {
                    navigateUnivMajor()
                }
            }
    }

    NicknameScreen(
        nickname = uiState.userInfo.nickname,
        errorMessage = uiState.nicknameErrorMessage,
        onNicknameChanged = { nickname ->
            val filteredNickname = nickname.filter { it.isKoreanChar() }
            viewModel.setEvent(AuthContract.Event.OnNicknameChanged(filteredNickname))
        },
        navigateUnivMajor = { viewModel.setEvent(AuthContract.Event.ValidateNickname) },
        onBackClick = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateBack) }
    )
}


@Composable
private fun NicknameScreen(
    nickname: String,
    errorMessage: String?,
    onNicknameChanged: (String) -> Unit,
    navigateUnivMajor: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        NickNameInputSection(
            nickname = nickname,
            onNicknameChanged = onNicknameChanged,
            errorMessage = errorMessage,
            onBackClick = onBackClick,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Center)
        )

        GongBaekBasicButton(
            title = "다음",
            enabled = nickname.isNotBlank() && errorMessage.isNullOrEmpty(),
            onClick = navigateUnivMajor,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}


@Composable
private fun NickNameInputSection(
    nickname: String,
    onNicknameChanged: (String) -> Unit,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Column {
        StartTitleTopBar(onClick = onBackClick)

        GongBaekProgressBar(progressPercent = 0.25f)

        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            PageDescriptionSection(
                titleResId = R.string.auth_nickname_title,
                descriptionResId = R.string.auth_select_profile_description
            )

            Spacer(modifier = Modifier.height(44.dp))

            GongBaekBasicTextField(
                value = nickname,
                onValueChange = onNicknameChanged,
                gongBaekBasicTextFieldType = GongBaekBasicTextFieldType.NICKNAME,
                isError = !errorMessage.isNullOrEmpty(),
                errorMessage = errorMessage.orEmpty(), // 에러 메시지 전달
                onErrorChange = { /* 에러 변경 처리 */ }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun PreviewNicknameScreen() {
    GONGBAEKTheme {
        NicknameScreen(
            nickname = "닉네임",
            onNicknameChanged = {},
            errorMessage = null,
        )
    }
}
