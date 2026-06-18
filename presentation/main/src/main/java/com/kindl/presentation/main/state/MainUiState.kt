package com.kindl.presentation.main.state

data class MainUiState(
    val isBottomBarVisible: Boolean = false,
)

sealed interface MainSideEffect
