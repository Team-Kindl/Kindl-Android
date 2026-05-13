package com.kindl.presentation.home

import androidx.lifecycle.ViewModel
import com.kindl.domain.forbidden.usecase.GetInstalledAppsUseCase
import com.kindl.presentation.home.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getInstalledAppsUseCase: GetInstalledAppsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getInstalledApps()
    }

    private fun getInstalledApps() {
        val installedApps = getInstalledAppsUseCase()
        _state.update { currentState ->
            currentState.copy(
                forbiddenApps = installedApps.map { it.toUiModel() }.toImmutableList()
            )
        }
    }
}
