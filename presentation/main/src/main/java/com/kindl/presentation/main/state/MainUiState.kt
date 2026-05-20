package com.kindl.presentation.main.state

import android.content.Intent
import com.kindl.core.permission.PermissionType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MainUiState(
    val missingPermissions: ImmutableList<PermissionType> = persistentListOf(),
    val isPermissionCheckComplete: Boolean = false,
    val isPermanentlyDenied: Boolean = false,
    val notificationDeniedCount: Int = 0,
) {
    val hasPermissions: Boolean get() = missingPermissions.isEmpty()
}

sealed interface MainSideEffect {
    data class OpenSettings(val intent: Intent?) : MainSideEffect
}
