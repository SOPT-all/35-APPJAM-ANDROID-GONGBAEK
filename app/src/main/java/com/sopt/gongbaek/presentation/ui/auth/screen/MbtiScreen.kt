package com.sopt.gongbaek.presentation.ui.auth.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.type.SelectableButtonType
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekBasicButton
import com.sopt.gongbaek.presentation.ui.component.button.GongBaekSelectableButtons
import com.sopt.gongbaek.presentation.ui.component.progressBar.GongBaekProgressBar
import com.sopt.gongbaek.presentation.ui.component.section.PageDescriptionSection
import com.sopt.gongbaek.presentation.ui.component.topbar.StartTitleTopBar
import com.sopt.gongbaek.ui.theme.GONGBAEKTheme
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun MbtiRoute(
    viewModel: AuthViewModel,
    navigateGender: () -> Unit,
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
                if (sideEffect is AuthContract.SideEffect.NavigateGender) {
                    navigateGender()
                }
            }
    }

    MbtiScreen(
        mbti = uiState.userInfo.mbti,
        energyDirectionOptions = uiState.energyDirectionOptions,
        informationGatheringOptions = uiState.informationGatheringOptions,
        decisionMakingOptions = uiState.decisionMakingOptions,
        lifestyleOrientationOptions = uiState.lifestyleOrientationOptions,
        onEnergyDirectionOptionSelected = { viewModel.setEvent(AuthContract.Event.OnEnergyDirectionOptionSelected(it)) },
        onInformationGatheringOptionSelected = { viewModel.setEvent(AuthContract.Event.OnInformationGatheringOptionSelected(it)) },
        onDecisionMakingOptionSelected = { viewModel.setEvent(AuthContract.Event.OnDecisionMakingOptionSelected(it)) },
        onLifestyleOrientationOptionSelected = { viewModel.setEvent(AuthContract.Event.OnLifestyleOrientationOptionSelected(it)) },
        navigateBack = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateBack) },
        navigateGender = { viewModel.sendSideEffect(AuthContract.SideEffect.NavigateGender) }
    )
}

@Composable
private fun MbtiScreen(
    mbti: String,
    energyDirectionOptions: String,
    informationGatheringOptions: String,
    decisionMakingOptions: String,
    lifestyleOrientationOptions: String,
    onEnergyDirectionOptionSelected: (String) -> Unit,
    onInformationGatheringOptionSelected: (String) -> Unit,
    onDecisionMakingOptionSelected: (String) -> Unit,
    onLifestyleOrientationOptionSelected: (String) -> Unit,
    navigateGender: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        bottomBar = {
            GongBaekBasicButton(
                title = "다음",
                enabled = mbti.length == 4,
                onClick = navigateGender,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    ) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            MbtiScreenContent(
                onBackClick = navigateBack,
                energyDirectionOptions = energyDirectionOptions,
                onEnergyDirectionOptionSelected = onEnergyDirectionOptionSelected,
                informationGatheringOptions = informationGatheringOptions,
                onInformationGatheringOptionSelected = onInformationGatheringOptionSelected,
                decisionMakingOptions = decisionMakingOptions,
                onDecisionMakingOptionSelected = onDecisionMakingOptionSelected,
                lifestyleOrientationOptions = lifestyleOrientationOptions,
                onLifestyleOrientationOptionSelected = onLifestyleOrientationOptionSelected,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun MbtiScreenContent(
    energyDirectionOptions: String,
    informationGatheringOptions: String,
    decisionMakingOptions: String,
    lifestyleOrientationOptions: String,
    onEnergyDirectionOptionSelected: (String) -> Unit,
    onInformationGatheringOptionSelected: (String) -> Unit,
    onDecisionMakingOptionSelected: (String) -> Unit,
    onLifestyleOrientationOptionSelected: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        StartTitleTopBar(onClick = onBackClick)

        GongBaekProgressBar(progressPercent = 0.625f)

        Column(
            modifier = modifier
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            PageDescriptionSection(
                titleResId = R.string.auth_mbti_title,
                descriptionResId = R.string.auth_mbti_description
            )

            Spacer(modifier = Modifier.height(42.dp))

            LazyColumn {
                item {
                    Text(
                        text = stringResource(R.string.auth_mbti_i_e),
                        color = GongBaekTheme.colors.gray08,
                        style = GongBaekTheme.typography.body2.sb14
                    )
                    GongBaekSelectableButtons(
                        selectableButtonType = SelectableButtonType.MBTI_FIRST,
                        onOptionSelected = onEnergyDirectionOptionSelected,
                        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                        selectedOption = energyDirectionOptions
                    )
                }

                item {
                    Text(
                        text = stringResource(R.string.auth_mbti_n_s),
                        color = GongBaekTheme.colors.gray08,
                        style = GongBaekTheme.typography.body2.sb14
                    )
                    GongBaekSelectableButtons(
                        selectableButtonType = SelectableButtonType.MBTI_SECOND,
                        onOptionSelected = onInformationGatheringOptionSelected,
                        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                        selectedOption = informationGatheringOptions
                    )
                }

                item {
                    Text(
                        text = stringResource(R.string.auth_mbti_t_f),
                        color = GongBaekTheme.colors.gray08,
                        style = GongBaekTheme.typography.body2.sb14
                    )
                    GongBaekSelectableButtons(
                        selectableButtonType = SelectableButtonType.MBTI_THIRD,
                        onOptionSelected = onDecisionMakingOptionSelected,
                        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                        selectedOption = decisionMakingOptions
                    )
                }

                item {
                    Text(
                        text = stringResource(R.string.auth_mbti_p_j),
                        color = GongBaekTheme.colors.gray08,
                        style = GongBaekTheme.typography.body2.sb14
                    )
                    GongBaekSelectableButtons(
                        selectableButtonType = SelectableButtonType.MBTI_FOURTH,
                        onOptionSelected = onLifestyleOrientationOptionSelected,
                        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                        selectedOption = lifestyleOrientationOptions
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMbtiScreen() {
    GONGBAEKTheme {
        MbtiScreen(
            navigateGender = {},
            navigateBack = {},
            mbti = "",
            energyDirectionOptions = "",
            informationGatheringOptions = "",
            decisionMakingOptions = "",
            lifestyleOrientationOptions = "",
            onEnergyDirectionOptionSelected = { },
            onInformationGatheringOptionSelected = { },
            onDecisionMakingOptionSelected = { },
            onLifestyleOrientationOptionSelected = { }
        )
    }
}
