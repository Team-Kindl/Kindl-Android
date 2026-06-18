package com.kindl.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(

): ViewModel() {

    private val _sideEffect = MutableSharedFlow<SplashSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        checkLoginState()
    }

    fun checkLoginState() {
        viewModelScope.launch {
            delay(2000)

            _sideEffect.emit(SplashSideEffect.NavigateToAuth)
        }
    }

}
