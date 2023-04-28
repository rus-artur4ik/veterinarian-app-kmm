package com.rus_artur4ik.petcore.mvvm.lce

sealed class ExtendedLceState<L,C,E> {

    data class Loading<L,C,E>(val state: L): ExtendedLceState<L,C,E>(), LceState.Loading<C>

    data class Content<L,C,E>(override val content: C): ExtendedLceState<L,C,E>(), LceState.Content<C>

    data class Error<L,C,E>(override val throwable: Throwable, val state: E): ExtendedLceState<L,C,E>(), LceState.Error<C>

    fun doIfContent(action: (C) -> Unit) {
        if (this is Content<L,C,E>) action(this.content as C)
    }
    fun doIfLoading(action: () -> Unit) {
        if (this is Loading<L,C,E>) action()
    }
    fun doIfError(action: (Throwable) -> Unit) {
        if (this is Error<L,C,E>) action(this.throwable)
    }

    fun contentOrNull(): Content<L,C,E>? {
        return this as? Content<L,C,E>
    }
    fun loadingOrNull(): Loading<L,C,E>? {
        return this as? Loading<L,C,E>
    }
    fun errorOrNull(): Error<L,C,E>? {
        return this as? Error<L,C,E>
    }
}
