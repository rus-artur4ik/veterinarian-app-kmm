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
        Wrapper(viewModel) {
            when (val state = viewModel.state) {
                is Loading -> Loading(
                    state = state,
                    viewModel = viewModel
                )
                is Content -> Content(
                    state = state,
                    viewModel = viewModel
                )
                is Error -> Error(
                    state = state,
                    viewModel = viewModel
                )
            }
        }
    }

    @Composable
    protected abstract fun Loading(
        state: Loading<S>,
        viewModel: VM
    )

    @Composable
    protected abstract fun Content(
        state: Content<S>,
        viewModel: VM
    )

    @Composable
    protected abstract fun Error(
        state: Error<S>,
        viewModel: VM
    )

    @Composable
    protected open fun Wrapper(viewModel: VM, content: @Composable () -> Unit) {
        content()
    }
}