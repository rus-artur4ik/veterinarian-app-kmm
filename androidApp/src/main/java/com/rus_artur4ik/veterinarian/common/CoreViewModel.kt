package com.rus_artur4ik.veterinarian.common

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class CoreViewModel<S : CoreState> : ViewModel() {

    private val _state by lazy { mutableStateOf(provideInitialScreenState()) }

    val state get() = _state as State<S>

    abstract fun provideInitialScreenState(): S

    protected fun emitState(newState: S) {
        _state.value = newState
    }
}