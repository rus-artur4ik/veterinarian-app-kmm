package com.rus_artur4ik.veterinarian.common.mvvm

import android.util.Log
import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.petcore.mvvm.lce.LceeViewModel
import com.rus_artur4ik.veterinarian.VetScreen
import com.rus_artur4ik.veterinarian.data.exception.UnauthorizedException

abstract class BaseEmptyableViewModel<S>: LceeViewModel<S>() {

    override fun emitState(newState: LceState<S>) {
        expectAuthorized {
            super.emitState(newState)
        }
    }

    override fun emitStateAsync(
        state: suspend () -> LceState<S>?,
        onError: (Exception) -> LceState<S>?
    ) {
        super.emitStateAsync(state) {
            if (it is UnauthorizedException) {
                onUnauthorized(it)
                null
            } else {
                onError(it)
            }
        }
    }

    override fun emitStateAsync(
        onContent: suspend (S) -> LceState.Content<S>?,
        onError: (Exception) -> LceState.Error<S>?,
        state: suspend () -> S,
    ) {
        super.emitStateAsync(
            state = state,
            onError = {
                if (it is UnauthorizedException) {
                    onUnauthorized(it)
                    null
                } else {
                    LceState.Error(it)
                }
            },
            onContent = { LceState.Content(it) }
        )
    }

    fun expectAuthorized(block: () -> Unit) {
        try {
            block()
        } catch (e: UnauthorizedException) {
            onUnauthorized(e)
        }
    }

    suspend fun expectAuthorizedAsync(block: suspend () -> Unit) {
        try {
            block()
        } catch (e: UnauthorizedException) {
            onUnauthorized(e)
        }
    }

    private fun onUnauthorized(e: UnauthorizedException) {
        Log.e("Authorization", e.toString())
        navigate(VetScreen.WelcomeScreen)
    }
}