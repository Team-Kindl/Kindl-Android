package com.kindl.auth.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kindl.auth.AuthRoute
import com.kindl.core.navigation.route.Route
import kotlinx.serialization.Serializable

fun NavController.navigateToAuth(
    navOptions: NavOptions? = null,
) {
    navigate(Auth, navOptions)
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.authNavGraph(
    paddingValues: PaddingValues,
    sharedTransitionScope: SharedTransitionScope,
    navigateToOnboarding: () -> Unit
) {
    composable<Auth> {
        with(sharedTransitionScope) {
            AuthRoute(
                paddingValues = paddingValues,
                animatedVisibilityScope = this@composable,
                navigateToOnboarding = navigateToOnboarding
            )
        }
    }
}

@Serializable
data object Auth : Route
