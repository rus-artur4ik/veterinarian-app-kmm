package com.rus_artur4ik.petcore.mvvm.lce

abstract class LceeViewModel<S>: LceViewModel<S>() {

    abstract fun isContentEmpty(content: S): Boolean
}