package com.kindl.splash

sealed interface SplashSideEffect {
    data object NavigateToAuth : SplashSideEffect
}
