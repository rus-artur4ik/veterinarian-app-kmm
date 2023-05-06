package com.rus_artur4ik.petcore.mvvm.lce

import androidx.compose.runtime.Composable
import com.rus_artur4ik.petcore.mvvm.MvvmScreen
import com.rus_artur4ik.petcore.mvvm.lce.LceState.Content
import com.rus_artur4ik.petcore.mvvm.lce.LceState.Error
import com.rus_artur4ik.petcore.mvvm.lce.LceState.Loading

abstract class LceScreen<S, VM: LceViewModel<S>>(
    viewModelClass: Class<VM>
): MvvmScreen<LceState<S>, VM>(viewModelClass) {

    @Composable
    final override fun Content(viewModel: VM) {
        Wrapper(viewModel = { viewModel }) {
            when (val state = viewModel.state) {
                is Loading -> Loading(
                    viewModel = viewModel
                )

                is Content -> Content(
                    content = state.content,
                    viewModel = viewModel
                )

                is Error -> Error(
                    throwable = state.throwable,
                    viewModel = viewModel
                )
            }
        }
    }

    @Composable
    protected abstract fun Wrapper(viewModel: () -> VM, content: @Composable () -> Unit)

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
}