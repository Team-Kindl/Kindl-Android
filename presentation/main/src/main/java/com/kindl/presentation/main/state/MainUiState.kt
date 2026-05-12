package com.kindl.presentation.main.state

import com.kindl.core.permission.PermissionType

data class MainUiState(
    val missingPermissions: List<PermissionType> = emptyList(),
    val isPermissionCheckComplete: Boolean = false,
) {
    val hasPermissions: Boolean get() = missingPermissions.isEmpty()
}

sealed interface MainSideEffect {
    data object CheckPermissions : MainSideEffect
    data class OpenSettings(val type: PermissionType) : MainSideEffect
}
