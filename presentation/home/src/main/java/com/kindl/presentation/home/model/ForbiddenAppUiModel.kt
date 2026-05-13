package com.kindl.presentation.home.model

import androidx.compose.runtime.Immutable
import com.kindl.domain.forbidden.model.InstalledAppModel

@Immutable
data class ForbiddenAppUiModel(
    val appIcon: String = "",
    val appName: String = "",
)

internal fun InstalledAppModel.toUiModel() = ForbiddenAppUiModel(
    appIcon = packageName,
    appName = appName
)
