package com.kindl.onboarding.component.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kindl.core.designsystem.component.button.KindlButton
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.onboarding.model.OnboardingMainInfoItem

@Composable
internal fun OnboardingMainInfo(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(
                color = KindlTheme.colors.slate800,
                shape = RoundedCornerShape(24.dp)
            )
            .dropShadow(
                shape = RoundedCornerShape(24.dp),
                shadow = Shadow(
                    color = Color(0xFF000000).copy(alpha = 0.1f),
                    radius = 10.dp,
                    spread = (-5).dp
                )
            )
            .padding(start = 48.dp, end = 48.dp, top = 48.dp, bottom = 53.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "스마트폰 과의존,\n" +
                    "이제는 달라져보세요",
            style = KindlTheme.typography.bold.headLine1,
            color = KindlTheme.colors.white,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = buildAnnotatedString {
                append("재택근무자, 학생, 취업준비생 등\n")
                append("집중이 필요한 현대인들을 위한\n")

                withStyle(
                    style = SpanStyle(
                        color = KindlTheme.colors.coral500
                    )
                ) {
                    append("실질적 리스크")
                }

                append("로 집중력 관리")
            },
            style = KindlTheme.typography.semiBold.title3,
            color = KindlTheme.colors.slate200,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        OnboardingMainInfoItem.entries.forEach { item ->
            OnboardingInfoItem(
                title = item.title,
                description = item.description,
                iconRes = item.iconRes,
            )
        }

        Spacer(modifier = Modifier.height(63.dp))

        KindlButton(
            text = "시작하기",
            onClick = onStartClick
        )
    }
}

@Composable
internal fun OnboardingInfoItem(
    title: String,
    description: String,
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = KindlTheme.colors.slate600,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = KindlTheme.colors.slate500,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(24.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = null,
            modifier = Modifier
                .background(
                    color = KindlTheme.colors.coral500,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            style = KindlTheme.typography.bold.headLine2,
            color = KindlTheme.colors.white
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            style = KindlTheme.typography.regular.body4,
            color = KindlTheme.colors.slate200
        )
    }
}

@Preview
@Composable
private fun OnboardingMainInfoPreview() {
    KindlTheme {
        OnboardingMainInfo(
            onStartClick = {}
        )
    }
}
