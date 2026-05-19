package com.kindl.core.permission

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.PowerManager
import android.os.Process
import android.provider.Settings

internal fun interface PermissionChecker {
    fun isGranted(context: Context): Boolean

    object UsageStats : PermissionChecker {
        override fun isGranted(context: Context): Boolean {
            val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                appOps.unsafeCheckOpNoThrow(
                    AppOpsManager.OPSTR_GET_USAGE_STATS,
                    Process.myUid(),
                    context.packageName,
                )
            } else {
                appOps.checkOpNoThrow(
                    AppOpsManager.OPSTR_GET_USAGE_STATS,
                    Process.myUid(),
                    context.packageName,
                )
            }
            return mode == AppOpsManager.MODE_ALLOWED
        }
    }

    object Overlay : PermissionChecker {
        override fun isGranted(context: Context): Boolean =
            Settings.canDrawOverlays(context)
    }

    object BatteryOptimization : PermissionChecker {
        override fun isGranted(context: Context): Boolean {
            val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            return pm.isIgnoringBatteryOptimizations(context.packageName)
        }
    }

    object PostNotifications : PermissionChecker {
        override fun isGranted(context: Context): Boolean =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) ==
                        PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
    }

    object Accessibility : PermissionChecker {
        override fun isGranted(context: Context): Boolean {
            val enabledServices = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
            ) ?: return false
            return enabledServices.split(":")
                .any { it.startsWith(context.packageName) }
        }
    }

    companion object {
        fun allGranted(context: Context): Boolean =
            listOf(
                UsageStats,
                Overlay,
                BatteryOptimization,
                PostNotifications,
                Accessibility,
            ).all { it.isGranted(context) }
    }
}
