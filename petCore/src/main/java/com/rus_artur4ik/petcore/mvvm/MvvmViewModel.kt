package com.rus_artur4ik.petcore.mvvm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.rus_artur4ik.petcore.navigation.Navigator.navigateTo
import com.rus_artur4ik.petcore.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class MvvmViewModel<S : MvvmState> : ViewModel() {

    var navHostController: NavHostController? = null
        private set

    val state get() = (_state as State<S>).value

    private val _state by lazy { mutableStateOf(provideInitialScreenState()) }

    abstract fun provideInitialScreenState(): S

    internal fun setNavController(navHostController: NavHostController?) {
        this.navHostController = navHostController
    }

    protected fun emitState(newState: S) {
        _state.value = newState
    }

    protected fun navigate(screen: Screen) {
        requireNotNull(navHostController).navigateTo(screen)
    }

    protected fun emitStateAsync(state: suspend () -> S, onError: (Exception) -> S) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val result: S
                withContext(Dispatchers.IO) {
                    result = state()
                }
                emitState(result)
            } catch (e: Exception) {
                Log.e("MvvmViewModel", "Error while emitting state: $e\ncause: ${e.cause}")
                emitState(onError(e))
            }
        }
    }
}