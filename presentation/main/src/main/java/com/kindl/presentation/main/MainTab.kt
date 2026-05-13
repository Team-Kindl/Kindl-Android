package com.kindl.presentation.main

import androidx.annotation.DrawableRes
import com.kindl.core.navigation.MainTabRoute
import com.kindl.core.navigation.Route
import com.kindl.presentation.home.navigation.Home

internal enum class MainTab(
    @param:DrawableRes val iconRes: Int,
    val route: MainTabRoute,
    val label: String,
) {
    HOME(
        iconRes = R.drawable.baseline_home_24,
        route = Home,
        label = "홈",
    );

    companion object {
        fun find(predicate: (MainTabRoute) -> Boolean): MainTab? = entries.find { predicate(it.route) }

        fun contains(predicate: (Route) -> Boolean): Boolean = entries.map { it.route }.any { predicate(it) }
    }
}
