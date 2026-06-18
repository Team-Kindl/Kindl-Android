package com.kindl.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.kindl.core.common.extension.collectSideEffect
import com.kindl.core.designsystem.R
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.splash.model.animation.LogoBoundsTransform

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SplashRoute(
    paddingValues: PaddingValues,
    navigateToAuth: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: SplashViewModel = hiltViewModel()
) {
    viewModel.sideEffect.collectSideEffect {
        when (it) {
            SplashSideEffect.NavigateToAuth -> navigateToAuth()
        }
    }

    SplashScreen(
        paddingValues = paddingValues,
        animatedVisibilityScope = animatedVisibilityScope
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.SplashScreen(
    paddingValues: PaddingValues,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = KindlTheme.colors.slate800
            )
            .padding(paddingValues)
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(301f))

        Image(
            painter = painterResource(R.drawable.img_icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp, 90.dp)
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "kindl_logo_image"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = LogoBoundsTransform
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Kindl",
            style = KindlTheme.typography.extraBold.title2,
            fontSize = 50.sp,
            color = KindlTheme.colors.slate200,
            modifier = Modifier.sharedElement(
                sharedContentState = rememberSharedContentState(key = "kindl_logo_text"),
                animatedVisibilityScope = animatedVisibilityScope
            )
        )

        Spacer(modifier = Modifier.weight(352f))
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun SplashScreenPreview() {
    KindlTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                this@SharedTransitionLayout.SplashScreen(
                    paddingValues = PaddingValues(),
                    animatedVisibilityScope = this@AnimatedVisibility
                )
            }
        }
    }
}
