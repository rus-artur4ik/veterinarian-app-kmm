package com.rus_artur4ik.petcore.mvvm.lce

import androidx.compose.runtime.Composable
import com.rus_artur4ik.petcore.mvvm.MvvmScreen

abstract class LceScreen<S, VM: LceViewModel<S>>(
    viewModelClass: Class<VM>
): MvvmScreen<LceState<S>, VM>(viewModelClass) {

    @Composable
    final override fun Content(viewModel: VM) {
        Wrapper(viewModel) {
            when (val state = viewModel.state) {
                is LceState.Loading -> Loading(viewModel)
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
    }

    @Composable
    protected abstract fun Loading(
        viewModel: VM
    )

    @Composable
    protected abstract fun Content(
        content: S,
        viewModel: VM
    )

    @Composable
    protected abstract fun Error(
        throwable: Throwable,
        viewModel: VM
    )

    @Composable
    protected open fun Wrapper(viewModel: VM, content: @Composable () -> Unit) {
        content()
    }
}