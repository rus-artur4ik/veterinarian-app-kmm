package com.rus_artur4ik.petcore.mvvm.lce

import com.rus_artur4ik.petcore.mvvm.MvvmState

sealed interface LceState<T>: MvvmState {

    interface Loading<T>: LceState<T>

    interface Content<T>: LceState<T> {
        val content: T
    }

    interface Error<T>: LceState<T> {
        val throwable: Throwable
    }


}