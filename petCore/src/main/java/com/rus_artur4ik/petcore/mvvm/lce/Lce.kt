package com.rus_artur4ik.petcore.mvvm.lce

sealed class Lce {

    object Loading: Lce()
    object Content: Lce()
    data class Error(open val throwable: Throwable): Lce()

    fun isLoading(): Boolean = this is Loading
    fun isContent(): Boolean = this is Content
    fun isError(): Boolean = this is Error
}