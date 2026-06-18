package com.kindl.splash.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kindl.core.navigation.Route
import com.kindl.splash.SplashRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSplash(
    navOptions: NavOptions? = null,
) {
    navigate(Splash, navOptions)
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.splashNavGraph(
    paddingValues: PaddingValues,
    sharedTransitionScope: SharedTransitionScope,
    navigateToAuth: () -> Unit,
) {
    composable<Splash> {
        with(sharedTransitionScope) {
            SplashRoute(
                paddingValues = paddingValues,
                navigateToAuth = navigateToAuth,
                animatedVisibilityScope = this@composable
            )
        }
    }
}

@Serializable
data object Splash : Route
