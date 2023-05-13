package com.rus_artur4ik.petcore.mvvm.lce

import com.rus_artur4ik.petcore.mvvm.MvvmViewModel
import com.rus_artur4ik.petcore.mvvm.lce.LceState.Lce.Content
import com.rus_artur4ik.petcore.mvvm.lce.LceState.Lce.Error

abstract class LceViewModel<S> : MvvmViewModel<LceState<S>>() {

    override fun provideInitialScreenState(): LceState<S> = LceState()

    protected open fun emitStateAsync(
        onContent: (S) -> LceState<S>? = { LceState(Content(), it) },
        onError: (Exception) -> LceState<S>? = { LceState(lce = Error(it), state = null) },
        state: suspend () -> S,
    ) {
        super.emitStateAsync(
            state = {
                LceState(
                    lce = Content(),
                    state = state()
                )
            },
            onError = {
                onError(it)
            }
        )
    }
}