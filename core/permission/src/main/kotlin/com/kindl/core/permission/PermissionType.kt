package com.kindl.core.permission

enum class PermissionType(val isFocusRequired: Boolean) {
    USAGE_STATS(isFocusRequired = true),
    OVERLAY(isFocusRequired = true),
    BATTERY_OPTIMIZATION(isFocusRequired = true),
    POST_NOTIFICATIONS(isFocusRequired = false),
    ACCESSIBILITY(isFocusRequired = false),
}
