package com.rus_artur4ik.petcore.mvvm.lce

import com.rus_artur4ik.petcore.mvvm.MvvmState

sealed class LceState<T>: MvvmState {

    class Loading<T>: LceState<T>()

    data class Content<T>(val content: T): LceState<T>()

    data class Error<T>(val throwable: Throwable): LceState<T>()

    fun doIfContent(action: (T) -> Unit) {
        if (this is Content<T>) action(this.content)
    }
    fun doIfLoading(action: () -> Unit) {
        if (this is Loading<T>) action()
    }
    fun doIfError(action: (Throwable) -> Unit) {
        if (this is Error<T>) action(this.throwable)
    }

    fun contentOrNull(): Content<T>? {
        return this as? Content<T>
    }
    fun loadingOrNull(): Loading<T>? {
        return this as? Loading<T>
    }
    fun errorOrNull(): Error<T>? {
        return this as? Error<T>
    }
}