package com.rus_artur4ik.petcore.mvvm.lce

import com.rus_artur4ik.petcore.mvvm.MvvmViewModel

abstract class LceViewModel<S> : MvvmViewModel<LceState<S>>() {

    protected fun emitStateAsync(state: suspend () -> S) {
        emitStateAsync(
            state = {
                LceState.Content(state())
            },
            onError = {
                LceState.Error(it)
            }
        )
    }
}