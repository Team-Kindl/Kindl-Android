package com.kindl.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kindl.onboarding.component.name.OnboardingProgressBar
import com.kindl.onboarding.model.OnboardingStep
import com.kindl.onboarding.screen.OnboardingMainScreen
import com.kindl.onboarding.screen.OnboardingNameScreen

@Composable
fun OnboardingRoute(
    paddingValues: PaddingValues,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column {
        OnboardingProgressBar(step = state.currentStep)

        when(state.currentStep) {
            OnboardingStep.MAIN -> {
                OnboardingMainScreen(
                    paddingValues = paddingValues,
                    onStartClick = viewModel::onNextClicked
                )
            }
            OnboardingStep.NAME -> {
                OnboardingNameScreen(
                    state = state,
                    onNicknameChange = viewModel::onNicknameChanged,
                    onClickNextButton = viewModel::onNextClicked
                )
            }
            OnboardingStep.FORBIDDEN -> {

            }
            OnboardingStep.POINT -> {

            }
            OnboardingStep.COMPLETE -> {

            }
        }
    }
}
