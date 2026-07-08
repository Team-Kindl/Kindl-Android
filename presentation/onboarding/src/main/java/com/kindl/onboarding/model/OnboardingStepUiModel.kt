package com.kindl.onboarding.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class OnboardingStepUiModel(
    // 첫 번째 스텝: 닉네임
    val nickname: String = "",

    // 두 번째 스텝: 금지앱 패키지 이름
    val forbiddenApps: PersistentList<String> = persistentListOf(),
)
