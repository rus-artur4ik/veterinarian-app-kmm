package com.rus_artur4ik.petcore.mvvm.lce

import androidx.compose.runtime.Composable
import com.rus_artur4ik.petcore.mvvm.MvvmScreen
import com.rus_artur4ik.petcore.mvvm.MvvmViewModel

abstract class LceScreen<T>(
    viewModelClass: Class<MvvmViewModel<LceState<T>>>
): MvvmScreen<LceState<T>, MvvmViewModel<LceState<T>>>(viewModelClass) {

    @Composable
    final override fun Content(viewModel: MvvmViewModel<LceState<T>>) {
        when (val state = viewModel.state) {
            LceState.Loading -> Loading(viewModel)
            is LceState.Content -> Content(
                content = state.content,
                viewModel = viewModel
            )
            is LceState.Error -> Error(
                throwable = state.throwable,
                viewModel = viewModel
            )
        }
    }

    @Composable
    protected abstract fun Loading(
        viewModel: MvvmViewModel<LceState<T>>
    )

    @Composable
    protected abstract fun Content(
        content: T,
        viewModel: MvvmViewModel<LceState<T>>
    )

    @Composable
    protected abstract fun Error(
        throwable: Throwable,
        viewModel: MvvmViewModel<LceState<T>>
    )

}