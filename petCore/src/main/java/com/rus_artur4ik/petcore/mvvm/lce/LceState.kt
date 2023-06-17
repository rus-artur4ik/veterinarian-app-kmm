package com.rus_artur4ik.petcore.mvvm.lce

import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.petcore.mvvm.lce.Lce.Content
import com.rus_artur4ik.petcore.mvvm.lce.Lce.Error
import com.rus_artur4ik.petcore.mvvm.lce.Lce.Loading

open class LceState<T>(
    open val lce: Lce = Loading,
    open val state: T? = null
): MvvmState {

    companion object {

        fun <T> loading(state: T? = null) = LceState(Loading, state)
        fun <T> content(content: T) = LceState(Content, content)
        fun <T> error(throwable: Throwable, state: T? = null) = LceState(Error(throwable), state)
    }

    fun doIfContent(action: (T) -> Unit) {
        val state = this.state
        if (lce is Content && state != null) action(state)
    }
    fun doIfLoading(action: () -> Unit) {
        if (lce is Loading) action()
    }
    fun doIfError(action: (Throwable) -> Unit) {
        val lce = this.lce
        if (lce is Error) action(lce.throwable)
    }

    fun throwableOrNull(): Throwable? {
        val lce = lce
        return if (lce is Error) lce.throwable else null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LceState<*>) return false

        if (lce != other.lce) return false
        if (state != other.state) return false

        return true
    }

    override fun hashCode(): Int {
        var result = lce.hashCode()
        result = 31 * result + (state?.hashCode() ?: 0)
        return result
    }
}