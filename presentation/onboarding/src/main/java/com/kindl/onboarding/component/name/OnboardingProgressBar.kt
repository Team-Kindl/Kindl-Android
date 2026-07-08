package com.kindl.onboarding.component.name

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.onboarding.model.OnboardingStep

@Composable
internal fun OnboardingProgressBar(
    step: OnboardingStep,
    modifier: Modifier = Modifier,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = step.progress,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing,
        ),
        label = "onboarding_progress",
    )

    Column(
        modifier = modifier
    ) {
        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier
                .fillMaxWidth(),
            color = KindlTheme.colors.coral500,
            trackColor = KindlTheme.colors.slate600,
        )

        if (step.showProgressBar) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${step.stepNumber} / ${step.totalSteps}",
                style = KindlTheme.typography.regular.body2,
                color = KindlTheme.colors.slate200,
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingProgressBarPreview() {
    KindlTheme {
        OnboardingProgressBar(
            step = OnboardingStep.NAME
        )
    }
}
