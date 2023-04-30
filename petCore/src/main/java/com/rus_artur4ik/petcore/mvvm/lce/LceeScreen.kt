package com.rus_artur4ik.petcore.mvvm.lce

import androidx.compose.runtime.Composable

abstract class LceeScreen<S, VM: LceeViewModel<S>>(
    viewModelClass: Class<VM>
): LceScreen<S, VM>(viewModelClass) {

    @Composable
    final override fun Content(
        content: S,
        viewModel: VM
    ) {
        Wrapper(viewModel = viewModel) {
            if (viewModel.isContentEmpty(content)) {
                Empty(
                    content = content,
                    viewModel = viewModel
                )
            } else {
                NotEmpty(
                    content = content,
                    viewModel = viewModel
                )
            }
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