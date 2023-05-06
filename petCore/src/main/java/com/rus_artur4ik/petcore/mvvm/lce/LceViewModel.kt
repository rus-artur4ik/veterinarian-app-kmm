package com.rus_artur4ik.petcore.mvvm.lce

import com.rus_artur4ik.petcore.mvvm.MvvmViewModel

abstract class LceViewModel<S> : MvvmViewModel<LceState<S>>() {

    override fun provideInitialScreenState(): LceState<S> = LceState.Loading()

    protected open fun emitStateAsync(
        onContent: suspend (S) -> LceState.Content<S>? = { LceState.Content(it) },
        onError: (Exception) -> LceState.Error<S>? = { LceState.Error(it) },
        state: suspend () -> S,
    ) {
        super.emitStateAsync(
            state = {
                onContent(state())
            },
            onError = {
                onError(it)
            }
        )
    }
}