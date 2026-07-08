package com.kindl.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kindl.core.navigation.workflow.FlowGraph
import com.kindl.domain.forbidden.usecase.GetInstalledAppsUseCase
import com.kindl.onboarding.model.OnboardingStep
import com.kindl.onboarding.model.OnboardingStepUiModel
import com.kindl.onboarding.model.toUiModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val onboardingFlow: FlowGraph<OnboardingStep, OnboardingStepUiModel>,
    private val forbiddenUseCase: GetInstalledAppsUseCase
): ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()

    private val _sideEffect = Channel<OnboardingSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        fetchInstalledApps()
    }

    private fun fetchInstalledApps() {
        viewModelScope.launch {
            val forbiddenApps = forbiddenUseCase()
            _state.update {
                it.copy(currentInstallApps = forbiddenApps.map { it.toUiModel() }.toImmutableList())
            }
        }
    }

    fun onNextClicked() {
        viewModelScope.launch {
            val currentState = _state.value
            if (!validateCurrentStep(currentState)) return@launch
            moveToNextStep(currentState)
        }
    }

    /**
     * 현재 스텝에 맞는 검증 로직
     * @return 검증 통과 여부 (true/false)
     */
    private suspend fun validateCurrentStep(state: OnboardingState): Boolean {
        return when (state.currentStep) {
            OnboardingStep.NAME -> validateNickname(state)
            else -> true
        }
    }

    /**
     * 닉네임 유효성 및 중복 검사를 수행
     */
    private suspend fun validateNickname(state: OnboardingState): Boolean {
        if (state.localNicknameError != null) {
            // TODO: UI에 에러 노출 (SideEffect로 emit)
            return false
        }
        // TODO: 서버 중복 검사 API 호출
        return true
    }

    /**
     * 다음 스텝으로 UI 상태를 업데이트
     */
    private fun moveToNextStep(currentState: OnboardingState) {
        val nextStep = onboardingFlow.getNextStep(
            currentState.currentStep,
            currentState.onboardingStepUiModel
        )

        if (nextStep != null) {
            _state.update {
                it.copy(currentStep = nextStep)
            }
        } else {
            // 서버 최종 전송
            // submitOnboarding()
        }
    }
}
