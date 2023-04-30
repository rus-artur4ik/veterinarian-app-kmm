package com.rus_artur4ik.veterinarian.common

import androidx.compose.runtime.Composable
import com.rus_artur4ik.petcore.mvvm.lce.LceScreen
import com.rus_artur4ik.petcore.mvvm.lce.LceViewModel
import com.rus_artur4ik.veterinarian.common.composables.ErrorIndicator
import com.rus_artur4ik.veterinarian.common.composables.LoadingIndicator

abstract class BaseScreen<S, VM: LceViewModel<S>>(
    viewModelClass: Class<VM>
) : LceScreen<S, VM>(viewModelClass) {

    @Composable
    override fun Wrapper(viewModel: VM, content: @Composable () -> Unit) {
        VetScreenTemplate(viewModel.navHostController) {
            content()
        }
    }

    @Composable
    override fun Loading(viewModel: VM) {
        LoadingIndicator()
    }

    @Composable
    override fun Error(throwable: Throwable, viewModel: VM) {
        ErrorIndicator(throwable)
    }
}
