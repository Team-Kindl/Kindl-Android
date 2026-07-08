package com.kindl.onboarding.model

import androidx.compose.runtime.Immutable
import com.kindl.domain.forbidden.model.InstalledAppModel

@Immutable
data class InstalledAppUiModel(
    val packageName: String,
    val appName: String,
)

internal fun InstalledAppModel.toUiModel() = InstalledAppUiModel(
    packageName = packageName,
    appName = appName
)
