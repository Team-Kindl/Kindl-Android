package com.kindl.core.permission.usecase

import com.kindl.core.permission.PermissionManager
import com.kindl.core.permission.PermissionType
import javax.inject.Inject

class CheckFocusPermissionsUseCase @Inject constructor(
    private val permissionManager: PermissionManager,
) {
    operator fun invoke(): List<PermissionType> =
        permissionManager.missingFocusPermissions()
}
