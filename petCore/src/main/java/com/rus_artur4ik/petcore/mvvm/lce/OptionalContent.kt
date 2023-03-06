package com.rus_artur4ik.petcore.mvvm.lce

data class OptionalContent<T>(
    val value: T?
) {

    fun isEmpty() = value == null
}