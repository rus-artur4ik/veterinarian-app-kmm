package com.rus_artur4ik.veterinarian.common.mvvm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.rus_artur4ik.veterinarian.common.Navigator.navigateTo
import com.rus_artur4ik.veterinarian.common.Screen

abstract class CoreViewModel<S : CoreState> : ViewModel() {

    private val _state by lazy { mutableStateOf(provideInitialScreenState()) }

    private var navHostController: NavHostController? = null

    val state get() = _state as State<S>

    abstract fun provideInitialScreenState(): S

    fun setNavHostController(navHostController: NavHostController?) {
        this.navHostController = navHostController
    }

    protected fun emitState(newState: S) {
        _state.value = newState
    }

    protected fun navigate(screen: Screen) {
        requireNotNull(navHostController).navigateTo(screen)
    }
}