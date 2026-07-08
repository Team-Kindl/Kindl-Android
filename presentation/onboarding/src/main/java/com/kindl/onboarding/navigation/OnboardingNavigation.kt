package com.kindl.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kindl.core.navigation.route.Route
import com.kindl.onboarding.OnboardingRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToOnboarding(
    navOptions: NavOptions? = null
) {
    navigate(Onboarding, navOptions)
}

fun NavGraphBuilder.onboardingNavGraph(
    paddingValues: PaddingValues
) {
    composable<Onboarding> {
        OnboardingRoute(
            paddingValues = paddingValues
        )
    }
}

@Serializable
data object Onboarding: Route
