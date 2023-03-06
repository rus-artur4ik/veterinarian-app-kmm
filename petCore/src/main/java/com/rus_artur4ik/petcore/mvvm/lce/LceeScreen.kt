package com.rus_artur4ik.petcore.mvvm.lce

import androidx.compose.runtime.Composable
import com.rus_artur4ik.petcore.mvvm.MvvmViewModel

private typealias LceeState<T> = LceState<OptionalContent<T>>

abstract class LceeScreen<T>(
    viewModelClass: Class<MvvmViewModel<LceeState<T>>>
): LceScreen<OptionalContent<T>>(viewModelClass) {

    @Composable
    final override fun Content(
        content: OptionalContent<T>,
        viewModel: MvvmViewModel<LceeState<T>>
    ) {
        if (content.isEmpty()) {
            Empty(viewModel = viewModel)
        } else {
            RequiredContent(
                content = requireNotNull(content.value),
                viewModel = viewModel
            )
        }
    }

    @Composable
    protected abstract fun Empty(viewModel: MvvmViewModel<LceeState<T>>)

    @Composable
    protected abstract fun RequiredContent(
        content: T,
        viewModel: MvvmViewModel<LceeState<T>>
    )
}