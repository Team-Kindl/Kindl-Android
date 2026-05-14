package com.kindl.presentation.home.model

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Stable

@Stable
data class ForbiddenAppUiModel(
    val appPackageName: String = "",
    val appName: String = "",
    val icon: Drawable? = null,
)
