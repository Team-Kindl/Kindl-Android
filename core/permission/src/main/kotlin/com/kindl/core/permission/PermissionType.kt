package com.kindl.core.permission

enum class PermissionType(val isFocusRequired: Boolean) {
    USAGE_STATS(true),
    OVERLAY(true),
    BATTERY_OPTIMIZATION(true),
    POST_NOTIFICATIONS(true),
}
