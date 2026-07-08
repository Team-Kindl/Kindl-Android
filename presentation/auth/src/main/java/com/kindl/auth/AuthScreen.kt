package com.kindl.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kindl.auth.component.AuthButtonHolder
import com.kindl.auth.component.AuthInfoHolder
import com.kindl.auth.component.AuthTermsHolder
import com.kindl.auth.component.TermsBottomSheet
import com.kindl.core.designsystem.theme.KindlTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AuthRoute(
    paddingValues: PaddingValues,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navigateToOnboarding: () -> Unit
) {
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    AuthScreen(
        paddingValues = paddingValues,
        showBottomSheet = showBottomSheet,
        animatedVisibilityScope = animatedVisibilityScope,
        onKakaoLogin = {
            showBottomSheet = !showBottomSheet
        },
        navigateToOnboarding = navigateToOnboarding // Todo: 로그인 성공 후 -> 모든 terms 동의 후 onboarding으로 이동
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.AuthScreen(
    paddingValues: PaddingValues,
    showBottomSheet: Boolean,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onKakaoLogin: () -> Unit,
    navigateToOnboarding: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(
                color = KindlTheme.colors.slate800
            )
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
    ) {
        Spacer(modifier = Modifier.weight(90f))

        AuthInfoHolder(
            animatedVisibilityScope = animatedVisibilityScope,
        )

        Spacer(modifier = Modifier.weight(207f))

        with(animatedVisibilityScope) {
            AuthButtonHolder(
                onKakaoLogin = onKakaoLogin,
                modifier = Modifier.animateEnterExit(
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> fullHeight / 2 },
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessVeryLow
                        )
                    ) + fadeIn(animationSpec = tween(durationMillis = 3000))
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            AuthTermsHolder(
                onClickTerms = {},
                modifier = Modifier.animateEnterExit(
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> (fullHeight / 2) + 100 },
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessVeryLow
                        )
                    ) + fadeIn(animationSpec = tween(durationMillis = 3000))
                )
            )
        }

        Spacer(modifier = Modifier.weight(80f))
    }

    if (showBottomSheet) {
        TermsBottomSheet(
            onDismiss = onKakaoLogin,
            onClickTerms = {},
            navigateToTerms = {},
            navigateToOnboarding = navigateToOnboarding
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun AuthScreenPreview() {
    KindlTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                this@SharedTransitionLayout.AuthScreen(
                    paddingValues = PaddingValues(),
                    showBottomSheet = true,
                    animatedVisibilityScope = this@AnimatedVisibility,
                    onKakaoLogin = {}
                )
            }
        }
    }
}
