package com.rus_artur4ik.petcore.mvvm.lce

import com.rus_artur4ik.petcore.mvvm.MvvmViewModel

abstract class LceViewModel<S> : MvvmViewModel<LceState<S>>() {

    override fun provideInitialScreenState(): LceState<S> = LceState()

    protected open fun emitStateAsync(
        onContent: (S) -> LceState<S>? = { LceState(Lce.Content, it) },
        onError: (Exception) -> LceState<S>? = { LceState(lce = Lce.Error(it), state = null) },
        state: suspend () -> S,
    ) {
        super.emitStateAsync(
            state = {
                LceState(
                    lce = Lce.Content,
                    state = state()
                )
            },
            onError = {
                onError(it)
            }
        )
    }
}