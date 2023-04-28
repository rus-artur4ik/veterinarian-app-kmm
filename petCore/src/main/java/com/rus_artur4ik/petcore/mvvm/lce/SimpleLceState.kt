package com.rus_artur4ik.petcore.mvvm.lce

sealed class SimpleLceState<T> {

    class Loading<T>: SimpleLceState<T>(), LceState.Loading<T>

    data class Content<T>(override val content: T): SimpleLceState<T>(), LceState.Content<T>

    data class Error<T>(override val throwable: Throwable): SimpleLceState<T>(), LceState.Error<T>

    fun doIfContent(action: (T) -> Unit) {
        if (this is Content<T>) action(this.content as T)
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