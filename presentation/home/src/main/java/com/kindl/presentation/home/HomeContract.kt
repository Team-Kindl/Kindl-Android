package com.kindl.presentation.home

import androidx.compose.runtime.Immutable
import com.kindl.presentation.home.model.ForbiddenAppUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class HomeState(
    val forbiddenApps: ImmutableList<ForbiddenAppUiModel> = persistentListOf(),
)