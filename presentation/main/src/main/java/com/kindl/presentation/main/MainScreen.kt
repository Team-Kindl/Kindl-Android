package com.kindl.presentation.main

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.core.app.ActivityCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.kindl.core.common.extension.collectSideEffect
import com.kindl.core.common.model.DialogTrigger
import com.kindl.core.common.model.GlobalUiEventHolder
import com.kindl.core.common.model.SnackbarState
import com.kindl.core.common.trigger.LocalGlobalUiEventTrigger
import com.kindl.core.permission.PermissionType
import com.kindl.presentation.home.navigation.homeNavGraph
import com.kindl.presentation.main.component.MainBottomBar
import com.kindl.presentation.main.component.permission.PermissionScreen
import com.kindl.presentation.main.state.MainAppState
import com.kindl.presentation.main.state.MainSideEffect
import com.kindl.presentation.main.state.rememberDialogStateHolder
import com.kindl.presentation.main.state.rememberMainAppState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("BatteryLife")
@Composable
internal fun MainScreen(
    appState: MainAppState = rememberMainAppState(),
    viewModel: MainViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val activity = LocalActivity.current
    val scope = rememberCoroutineScope()

    val isBottomBarVisible by appState.isBottomBarVisible.collectAsStateWithLifecycle()
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()
    val dialogState = rememberDialogStateHolder()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }
    var currentSnackbarState by remember { mutableStateOf<SnackbarState?>(null) }

    // POST_NOTIFICATIONS 런타임 권한 요청 런처
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.checkPermissions()
        } else {
            val isPermanentlyDenied = !ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!,
                Manifest.permission.POST_NOTIFICATIONS,
            )
            viewModel.onNotificationPermissionDenied(isPermanentlyDenied)
        }
    }

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

    viewModel.sideEffect.collectSideEffect {
        when (it) {
            is MainSideEffect.OpenSettings -> {
                context.startActivity(it.intent)
            }

            else -> {

            }
        }
    }

    if (state.isPermissionCheckComplete && !state.hasPermissions) {
        PermissionScreen(
            missingPermissions = state.missingPermissions,
            isPermanentlyDenied = state.isPermanentlyDenied,
            notificationDeniedCount = state.notificationDeniedCount,
            onRequestPermission = { type ->
                when (type) {
                    PermissionType.POST_NOTIFICATIONS -> {
                        val neverRequested = state.notificationDeniedCount == 0
                        val isPermanentlyDenied = !ActivityCompat.shouldShowRequestPermissionRationale(
                            activity!!,
                            Manifest.permission.POST_NOTIFICATIONS,
                        )
                        when {
                            // 처음 요청
                            neverRequested -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                }
                            }
                            // 영구 거절 → 설정으로
                            isPermanentlyDenied -> viewModel.onOpenSettings(type)
                            // 1회 거절 → 재요청
                            else -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                }
                            }
                        }
                    }
                    // 나머지는 항상 설정으로
                    else -> viewModel.onOpenSettings(type)
                }
            },
            onPermissionGranted = viewModel::checkPermissions,
        )
        return
    }

    CompositionLocalProvider(
        LocalGlobalUiEventTrigger provides eventHolder,
    ) {
        Scaffold (
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
                homeNavGraph(
                    paddingValues = paddingValues
                )
            }
        }
    }
}
