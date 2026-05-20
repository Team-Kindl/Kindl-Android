package com.focpet.core.localstorage.permissions

import kotlinx.coroutines.flow.Flow

interface PermissionInfoManager {
    suspend fun saveNotificationDeniedCount()

    val notificationDeniedCount: Flow<Int>
}
