package com.kindl.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kindl.core.designsystem.R
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.onboarding.component.main.OnboardingMainInfo

@Composable
internal fun OnboardingMainScreen(
    paddingValues: PaddingValues,
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = KindlTheme.colors.slate700,
            )
            .padding(paddingValues = paddingValues)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(64f))

        Image(
            painter = painterResource(R.drawable.img_icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp, 90.dp)
        )

        Text(
            text = "Kindl",
            style = KindlTheme.typography.extraBold.title2,
            fontSize = 50.sp,
            color = KindlTheme.colors.slate200,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(64.dp))

        OnboardingMainInfo(
            onStartClick = onStartClick
        )

        Spacer(modifier = Modifier.weight(58f))
    }
}

@Preview
@Composable
private fun OnboardingMainScreenPreview() {
    KindlTheme {
        OnboardingMainScreen(
            paddingValues = PaddingValues(),
            onStartClick = {}
        )
    }
}
