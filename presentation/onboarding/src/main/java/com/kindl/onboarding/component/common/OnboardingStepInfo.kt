package com.kindl.onboarding.component.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.presentation.onboarding.R

@Composable
internal fun OnboardingStepInfo(
    title: String,
    description: String,
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = null,
            modifier = Modifier
                .background(
                    color = KindlTheme.colors.coral500,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(20.dp),
            tint = Color.Unspecified
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = KindlTheme.typography.bold.headLine3,
                color = KindlTheme.colors.white
            )

            Text(
                text = description,
                style = KindlTheme.typography.regular.body2,
                color = KindlTheme.colors.slate200
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingStepInfoPreview() {
    KindlTheme {
        OnboardingStepInfo(
            title = "닉네임 설정",
            description = "집중방에서 사용할 닉네임을 적어주세요.",
            iconRes = R.drawable.ic_onboarding_name
        )
    }
}
