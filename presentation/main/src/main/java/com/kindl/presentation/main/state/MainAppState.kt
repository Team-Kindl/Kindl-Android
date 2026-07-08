package com.kindl.presentation.main.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kindl.auth.navigation.navigateToAuth
import com.kindl.onboarding.navigation.navigateToOnboarding
import com.kindl.presentation.home.navigation.Home
import com.kindl.presentation.main.MainTab
import com.kindl.splash.navigation.Splash
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
internal class MainAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
) {
    val startDestination = Splash

    /*val isOffline: StateFlow<Boolean> = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )*/

    private val currentDestination = navController.currentBackStackEntryFlow
        .map { it.destination }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val currentTab: StateFlow<MainTab?> = currentDestination
        .map { destination ->
            MainTab.find { tab ->
                destination?.hasRoute(tab::class) == true
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val isBottomBarVisible: StateFlow<Boolean?> = currentDestination
        .map { destination ->
            MainTab.contains { tab ->
                destination?.hasRoute(tab::class) == true
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }

        val refreshNavOptions = navOptions {
            popUpTo(0) {
                inclusive = true
            }
            launchSingleTop
        }

        when (tab) {
            MainTab.HOME -> navController.navigate(Home, navOptions)
        }
    }

    private val clearStackNavOptions = navOptions {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToAuth() {
        navController.navigateToAuth(clearStackNavOptions)
    }

    fun navigateToOnboarding() {
        navController.navigateToOnboarding(clearStackNavOptions)
    }
}

@Composable
internal fun rememberMainAppState(
    //networkMonitor: NetworkMonitor,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MainAppState = remember(navController, coroutineScope) {
    MainAppState(navController, coroutineScope)
}
