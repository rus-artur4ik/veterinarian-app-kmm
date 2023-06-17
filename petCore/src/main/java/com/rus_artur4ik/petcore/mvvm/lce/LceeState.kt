package com.rus_artur4ik.petcore.mvvm.lce

import com.rus_artur4ik.petcore.mvvm.MvvmState

data class LceeState<T>(
    val lce: Lcee,
    val state: T
): MvvmState{

    sealed class Lcee {
        object Loading : Lcee()
        object Content : Lcee()
        object Empty : Lcee()

        data class Error(val throwable: Throwable): Lcee()
    }
}