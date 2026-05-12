package com.kindl.core.permission

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.net.toUri

@Singleton
internal class PermissionManagerImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : PermissionManager {

    private val checkers: Map<PermissionType, PermissionChecker> = mapOf(
        PermissionType.USAGE_STATS to PermissionChecker.UsageStats,
        PermissionType.OVERLAY to PermissionChecker.Overlay,
        PermissionType.BATTERY_OPTIMIZATION to PermissionChecker.BatteryOptimization,
        PermissionType.POST_NOTIFICATIONS to PermissionChecker.PostNotifications,
    )

    private val focusRequiredPermissions =
        PermissionType.entries.filter { it.isFocusRequired }

    override fun check(type: PermissionType): Boolean =
        checkers[type]?.isGranted(context) ?: false

    override fun allFocusPermissionsGranted(): Boolean =
        focusRequiredPermissions.all { check(it) }

    override fun missingFocusPermissions(): List<PermissionType> =
        focusRequiredPermissions.filter { !check(it) }

    // intent만 반환하고 실제 이동은 ui 레이어에서
    override fun openSettings(type: PermissionType) : Intent {
        val packageUri = "package:${context.packageName}".toUri()
        val intent = when (type) {
            PermissionType.USAGE_STATS ->
                Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)

            PermissionType.OVERLAY ->
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, packageUri)

            PermissionType.BATTERY_OPTIMIZATION ->
                Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, packageUri)

            PermissionType.POST_NOTIFICATIONS ->
                Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                }
        }.apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        return intent
    }
}
