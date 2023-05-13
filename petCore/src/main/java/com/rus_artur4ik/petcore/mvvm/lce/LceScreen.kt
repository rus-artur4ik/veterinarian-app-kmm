package com.rus_artur4ik.petcore.mvvm.lce

import androidx.compose.runtime.Composable
import com.rus_artur4ik.petcore.mvvm.MvvmScreen
import com.rus_artur4ik.petcore.mvvm.lce.LceState.Lce.Content
import com.rus_artur4ik.petcore.mvvm.lce.LceState.Lce.Error
import com.rus_artur4ik.petcore.mvvm.lce.LceState.Lce.Loading

abstract class LceScreen<S, VM: LceViewModel<S>>(
    viewModelClass: Class<VM>
): MvvmScreen<LceState<S>, VM>(viewModelClass) {

    @Composable
    final override fun Content(viewModel: VM) {
        Wrapper(viewModel = { viewModel }) {
            when (val lce = viewModel.state.lce) {
                is Loading -> Loading(
                    viewModel = viewModel
                )

                is Content -> {
                    viewModel.state.state?.let {
                        Content(
                            content = it,
                            viewModel = viewModel
                        )
                    }
                }

                is Error -> Error(
                    throwable = lce.t,
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