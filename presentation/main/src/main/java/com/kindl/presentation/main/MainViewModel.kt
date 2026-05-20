package com.kindl.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.focpet.core.localstorage.permissions.PermissionInfoManager
import com.kindl.core.permission.PermissionManager
import com.kindl.core.permission.PermissionType
import com.kindl.core.permission.usecase.CheckFocusPermissionsUseCase
import com.kindl.presentation.main.state.MainSideEffect
import com.kindl.presentation.main.state.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkFocusPermissions: CheckFocusPermissionsUseCase,
    private val permissionInfoManager: PermissionInfoManager, // 설정 거부 검증용
    private val permissionManager: PermissionManager, // 실제 퍼미션 체크용
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MainSideEffect>()
    val sideEffect: SharedFlow<MainSideEffect> = _sideEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            val deniedCount = permissionInfoManager.notificationDeniedCount.first()
            _state.update {
                it.copy(notificationDeniedCount = deniedCount)
            }

            checkPermissions()
        }
    }

    fun checkPermissions() {
        val missingPermissions = checkFocusPermissions()
        _state.update {
            it.copy(
                missingPermissions = missingPermissions.toImmutableList(),
                isPermissionCheckComplete = true,
            )
        }
    }

    fun onNotificationPermissionDenied(isPermanentlyDenied: Boolean) {
        viewModelScope.launch {
            permissionInfoManager.saveNotificationDeniedCount()

            val deniedCount = permissionInfoManager.notificationDeniedCount.first()
            _state.update {
                it.copy(
                    isPermanentlyDenied = isPermanentlyDenied,
                    notificationDeniedCount = deniedCount,
                )
            }
        }
    }

    fun onOpenSettings(type: PermissionType) {
        viewModelScope.launch {
            val intent = permissionManager.openSettings(type)
            _sideEffect.emit(MainSideEffect.OpenSettings(intent))
        }
    }


}
