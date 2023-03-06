package com.rus_artur4ik.petcore.mvvm.lce

import com.rus_artur4ik.petcore.mvvm.MvvmState

sealed class LceState<T>: MvvmState() {

    object Loading: LceState<Unit>()

    data class Content<T>(val content: T): LceState<T>()

    data class Error(val throwable: Throwable): LceState<Throwable>()
}