package com.kindl.core.permission

import android.content.Intent

interface PermissionManager {
    fun check(type: PermissionType): Boolean
    fun allFocusPermissionsGranted(): Boolean
    fun missingFocusPermissions(): List<PermissionType>
    fun openSettings(type: PermissionType) : Intent?
}
