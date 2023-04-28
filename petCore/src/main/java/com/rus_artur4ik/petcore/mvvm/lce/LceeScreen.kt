package com.rus_artur4ik.petcore.mvvm.lce

import androidx.compose.runtime.Composable
import com.rus_artur4ik.petcore.mvvm.lce.LceState.Content

abstract class LceeScreen<S, VM: LceeViewModel<S>>(
    viewModelClass: Class<VM>
): LceScreen<S, VM>(viewModelClass) {

    @Composable
    final override fun Content(
        state: Content<S>,
        viewModel: VM
    ) {
        if (viewModel.isContentEmpty(state.content)) {
            Empty(
                content = state.content,
                viewModel = viewModel
            )
        } else {
            NotEmpty(
                content = state.content,
                viewModel = viewModel
            )
        }
    }

    @Composable
    protected abstract fun Empty(
        content: S,
        viewModel: VM
    )

    @Composable
    protected abstract fun NotEmpty(
        content: S,
        viewModel: VM
    )
}