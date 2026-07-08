package com.kindl.onboarding

import androidx.compose.runtime.Immutable
import com.kindl.onboarding.error.NicknameError
import com.kindl.onboarding.model.InstalledAppUiModel
import com.kindl.onboarding.model.OnboardingStep
import com.kindl.onboarding.model.OnboardingStepUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class OnboardingState(
    val currentStep: OnboardingStep = OnboardingStep.MAIN,
    val currentInstallApps: ImmutableList<InstalledAppUiModel> = persistentListOf(),
    val onboardingStepUiModel: OnboardingStepUiModel = OnboardingStepUiModel(),


) {
    val localNicknameError: NicknameError?
        get() {
            val length = onboardingStepUiModel.nickname.length
            return when {
                length < 2 -> NicknameError.TooShort
                length > 10 -> NicknameError.TooLong
                else -> null
            }
        }
}

sealed interface OnboardingSideEffect {

}
