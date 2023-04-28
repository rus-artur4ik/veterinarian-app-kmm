package com.rus_artur4ik.petcore.mvvm.lce

import com.rus_artur4ik.petcore.mvvm.MvvmViewModel

abstract class LceViewModel<S> : MvvmViewModel<LceState<S>>() {

    override fun provideInitialScreenState(): LceState<S> = SimpleLceState.Loading()

    protected fun emitStateAsync(state: suspend () -> S) {
        emitStateAsync(
            state = {
                SimpleLceState.Content(state())
            },
            onError = {
                SimpleLceState.Error(it)
            }
        )
    }
}