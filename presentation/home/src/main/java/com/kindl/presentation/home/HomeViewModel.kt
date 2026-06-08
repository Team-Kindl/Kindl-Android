package com.kindl.presentation.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kindl.domain.forbidden.usecase.GetInstalledAppsUseCase
import com.kindl.presentation.home.model.ForbiddenAppUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val getInstalledAppsUseCase: GetInstalledAppsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch(Dispatchers.IO) {
            val pm = context.packageManager
            val uiModels = getInstalledAppsUseCase().map { app ->
                ForbiddenAppUiModel(
                    appPackageName = app.packageName,
                    appName = app.appName,
                    icon = runCatching { pm.getApplicationIcon(app.packageName) }.getOrNull(),
                )
            }.toImmutableList()

            _state.update { it.copy(forbiddenApps = uiModels) }
        }
    }
}
