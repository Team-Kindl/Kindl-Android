package com.kindl.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kindl.core.navigation.route.MainTabRoute
import com.kindl.presentation.home.HomeRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues
) {
    composable<Home> {
        HomeRoute(
            paddingValues = paddingValues
        )
    }
}

@Serializable
data object Home: MainTabRoute
