package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.ui.auth.component.EmptySearchResultView
import com.sopt.gongbaek.presentation.ui.auth.component.SearchResultSection
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.topbar.CenterTitleTopBar
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.presentation.util.extension.roundedBackgroundWithBorder
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun UnivSearchRoute(
    viewModel: AuthViewModel,
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
            }
    }

    UnivSearchScreen(
        univ = uiState.univ,
        school = uiState.userInfo.school,
        selectedItem = uiState.univSearchSelectedItem,
        univSearchResult = uiState.universities.universities,
        onUnivChange = { newValue -> viewModel.setEvent(AuthContract.Event.OnSearchUnivChanged(newValue)) },
        onSearchButtonClicked = { viewModel.setEvent(AuthContract.Event.OnUnivSearchClick) },
        onItemSelected = { selectedUniv -> viewModel.setEvent(AuthContract.Event.OnUnivSelected(selectedUniv)) },
        navigateBack = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateBack) },
        onCloseClick = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateBack) }
    )
}

@Composable
private fun UnivSearchScreen(
    univ: String,
    school: String,
    selectedItem: String,
    onUnivChange: (String) -> Unit,
    onSearchButtonClicked: () -> Unit,
    univSearchResult: List<String>,
    onItemSelected: (String) -> Unit,
    onCloseClick: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterTitleTopBar(
                centerTitleResId = R.string.topbar_search,
                trailingIconResId = R.drawable.ic_x_48,
                textColor = GongBaekTheme.colors.gray10,
                textStyle = GongBaekTheme.typography.body1.sb16,
                onTrailingIconClick = onCloseClick
            )
        },
        bottomBar = {
            GongBaekBasicButton(
                title = "적용",
                enabled = school.isNotEmpty(),
                onClick = navigateBack,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        },
        containerColor = GongBaekTheme.colors.white,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 12.dp)
            ) {
                SearchTextField(
                    value = univ,
                    onValueChange = onUnivChange,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onSearchButtonClicked = onSearchButtonClicked
                )

                Spacer(modifier = Modifier.height(14.dp))

                if (univSearchResult.isEmpty()) {
                    EmptySearchResultView()
                }

                SearchResultSection(
                    univSearchResult = univSearchResult,
                    selectedItem = selectedItem,
                    onItemSelected = onItemSelected
                )
            }
        }
    )
}

@Composable
private fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onSearchButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "학교",
                color = GongBaekTheme.colors.gray08,
                style = GongBaekTheme.typography.body2.sb14
            )
        }

        var isFocused by remember { mutableStateOf(false) }

        val textStyle = GongBaekTheme.typography.body1.m16.copy(
            color = GongBaekTheme.colors.gray10
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .roundedBackgroundWithBorder(
                    cornerRadius = 6.dp,
                    backgroundColor = GongBaekTheme.colors.gray01,
                    borderColor = if (isFocused) GongBaekTheme.colors.gray10 else GongBaekTheme.colors.gray01,
                    borderWidth = 1.dp
                )
                .padding(start = 16.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            singleLine = true,
            textStyle = textStyle,
            cursorBrush = SolidColor(GongBaekTheme.colors.gray05)
        ) { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "학교 이름을 검색하세요.",
                            color = GongBaekTheme.colors.gray04,
                            style = GongBaekTheme.typography.body1.m16
                        )
                    }
                    innerTextField()
                }

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_search_black_48),
                    contentDescription = null,
                    tint = GongBaekTheme.colors.gray10,
                    modifier = Modifier.clickableWithoutRipple {
                        onSearchButtonClicked()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewUnivSearchScreen() {
    val universities = listOf(
        "한양대학교",
        "건국대학교 서울캠퍼스",
        "서울대학교",
        "건국대학교 서울캠퍼스",
        "고려대학교",
        "건국대학교 서울캠퍼스",
        "연세대학교",
        "한양대학교",
        "건국대학교 서울캠퍼스",
        "서울대학교",
        "건국대학교 서울캠퍼스",
        "고려대학교",
        "건국대학교 서울캠퍼스",
        "연세대학교"
    )

    GONGBAEKTheme {
        UnivSearchScreen(
            onCloseClick = {},
            univSearchResult = universities,
            onSearchButtonClicked = {},
            univ = "",
            onUnivChange = {},
            onItemSelected = {},
            school = "",
            navigateBack = {},
            selectedItem = ""
        )
    }
}
