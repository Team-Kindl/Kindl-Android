import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kindl.core.permission.PermissionManager
import com.kindl.core.permission.PermissionType
import com.kindl.core.permission.usecase.CheckFocusPermissionsUseCase
import com.kindl.presentation.main.state.MainSideEffect
import com.kindl.presentation.main.state.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkFocusPermissions: CheckFocusPermissionsUseCase,
    private val permissionManager: PermissionManager,
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MainSideEffect>()
    val sideEffect: SharedFlow<MainSideEffect> = _sideEffect.asSharedFlow()

    init {
        checkPermissions()
    }

    fun checkPermissions() {
        val missingPermissions = checkFocusPermissions()
        _state.update {
            it.copy(
                missingPermissions = missingPermissions,
                isPermissionCheckComplete = true,
            )
        }
    }

    fun openSettings(type: PermissionType) {
        permissionManager.openSettings(type)
    }
}
