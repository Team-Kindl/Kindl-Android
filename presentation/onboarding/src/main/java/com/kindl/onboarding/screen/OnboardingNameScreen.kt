package com.kindl.onboarding.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kindl.core.designsystem.component.button.KindlButton
import com.kindl.core.designsystem.component.textfield.KindlTextField
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.onboarding.OnboardingState
import com.kindl.onboarding.component.common.OnboardingStepInfo
import com.kindl.onboarding.model.OnboardingStep
import com.kindl.onboarding.model.OnboardingStepUiModel
import com.kindl.presentation.onboarding.R

@Composable
internal fun OnboardingNameScreen(
    state: OnboardingState,
    onNicknameChange: (String) -> Unit,
    onClickNextButton: () -> Unit,
) {
    val nicknameTextState = rememberTextFieldState(
        initialText = state.onboardingStepUiModel.nickname
    )

    LaunchedEffect(nicknameTextState) {
        snapshotFlow { nicknameTextState.text }
            .collect { newText ->
                onNicknameChange(newText.toString())
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 45.dp)
    ) {
        Spacer(modifier = Modifier.height(55.dp))

        OnboardingStepInfo(
            title = "닉네임 설정",
            description = "집중방에서 사용할 닉네임을 적어주세요.",
            iconRes = R.drawable.ic_onboarding_name
        )

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            text = "닉네임",
            style = KindlTheme.typography.medium.body16,
            color = KindlTheme.colors.white
        )

        Spacer(modifier = Modifier.height(8.dp))

        KindlTextField(
            state = nicknameTextState,
            placeholder = "닉네임을 입력해주세요"
        )

        Spacer(modifier = Modifier.weight(1f))

        KindlButton(
            text = "다음",
            suffixIcon = ImageVector.vectorResource(com.kindl.core.designsystem.R.drawable.ic_right_arrow),
            onClick = onClickNextButton
        )
    }

}


@Preview
@Composable
private fun OnboardingNameScreenPreview() {
    KindlTheme {
        OnboardingNameScreen(
            state = OnboardingState(
                currentStep = OnboardingStep.NAME,
                onboardingStepUiModel = OnboardingStepUiModel()
            ),
            onNicknameChange = {},
            onClickNextButton = {}
        )
    }
}
