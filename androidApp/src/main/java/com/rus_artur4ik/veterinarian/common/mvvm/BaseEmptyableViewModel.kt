package com.rus_artur4ik.veterinarian.common.mvvm

import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.petcore.mvvm.lce.LceeViewModel
import com.rus_artur4ik.veterinarian.VetScreen
import com.rus_artur4ik.veterinarian.data.exception.UnauthorizedException

abstract class BaseEmptyableViewModel<S> : LceeViewModel<S>() {

    override fun emitState(newState: LceState<S>) {
        expectAuthorized {
            super.emitState(newState)
        }
    }

    override fun emitStateAsync(
        onError: (Exception) -> LceState<S>?,
        state: suspend () -> LceState<S>?,
    ) {
        super.emitStateAsync(
            {
                if (it is UnauthorizedException) {
                    onUnauthorized(it)
                    null
                } else {
                    Firebase.crashlytics.recordException(it)
                    onError(it)
                }
            },
            { state() }
        )
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
                    Firebase.crashlytics.recordException(it)
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