package com.kindl.core.permission.usecase

import com.kindl.core.permission.PermissionManager
import javax.inject.Inject

class AreAllFocusPermissionsGrantedUseCase @Inject constructor(
    private val permissionManager: PermissionManager,
) {
    operator fun invoke(): Boolean =
        permissionManager.allFocusPermissionsGranted()
}
