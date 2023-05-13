package com.rus_artur4ik.petcore.mvvm.lce

data class LceeState<T>(
    override val lce: Lcee,
    override val state: T
): LceState<T>(){

    sealed class Lcee: Lce() {
        object Loading: Lce.Loading()
        object Content: Lce.Content()
        object Empty: Lce.Content()

        data class Error(override val t: Throwable): Lce.Error(t)
    }
}