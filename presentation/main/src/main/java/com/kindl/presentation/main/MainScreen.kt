package com.kindl.presentation.main

import android.widget.Toast
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.kindl.auth.navigation.authNavGraph
import com.kindl.core.common.model.DialogTrigger
import com.kindl.core.common.model.GlobalUiEventHolder
import com.kindl.core.common.model.SnackbarState
import com.kindl.core.common.trigger.LocalGlobalUiEventTrigger
import com.kindl.presentation.home.navigation.homeNavGraph
import com.kindl.presentation.main.component.MainBottomBar
import com.kindl.presentation.main.state.MainAppState
import com.kindl.presentation.main.state.rememberDialogStateHolder
import com.kindl.presentation.main.state.rememberMainAppState
import com.kindl.splash.navigation.splashNavGraph
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun MainScreen(
    appState: MainAppState = rememberMainAppState(),
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val isBottomBarVisible by appState.isBottomBarVisible.collectAsStateWithLifecycle()
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()
    val dialogState = rememberDialogStateHolder()

    val snackBarHostState = remember { SnackbarHostState() }
    var currentSnackbarState by remember { mutableStateOf<SnackbarState?>(null) }

    val onShowToast: (String) -> Unit = remember {
        { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    val onShowSnackbar: (SnackbarState) -> Unit = remember(scope, snackBarHostState) {
        { state ->
            currentSnackbarState = state
            scope.launch {
                snackBarHostState.currentSnackbarData?.dismiss()

                val job = launch {
                    snackBarHostState.showSnackbar(
                        message = state.message,
                    )
                }
                job.invokeOnCompletion {
                    if (currentSnackbarState == state) {
                        currentSnackbarState = null
                    }
                }
                delay(2000L)
                job.cancel()
            }
        }
    }

    val eventHolder = remember(dialogState, onShowToast, onShowSnackbar) {
        GlobalUiEventHolder(
            dialogTrigger = DialogTrigger(
                show = { onConfirm ->
                    dialogState.showDialog(onConfirm)
                },
                dismiss = {
                    dialogState.dismissDialog()
                }
            ),
            showToast = onShowToast,
            showSnackbar = onShowSnackbar,
        )
    }

    CompositionLocalProvider(
        LocalGlobalUiEventTrigger provides eventHolder,
    ) {
        Scaffold(
            bottomBar = {
                if (isBottomBarVisible == true) {
                    MainBottomBar(
                        tabs = MainTab.entries.toImmutableList(),
                        currentTab = currentTab,
                        onTabSelected = appState::navigate,
                    )
                }
            },
            modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->
            SharedTransitionLayout {
                NavHost(
                    navController = appState.navController,
                    startDestination = appState.startDestination,
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        )
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        )
                    },
                    popEnterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        )
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        )
                    },
                ) {
                    splashNavGraph(
                        paddingValues = paddingValues,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        navigateToAuth = appState::navigateToAuth
                    )

                    authNavGraph(
                        paddingValues = paddingValues,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )

                    homeNavGraph(
                        paddingValues = paddingValues
                    )

                }
            }
        }
    }
}
