package com.rus_artur4ik.petcore.mvvm

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

abstract class MvvmScreen<S : MvvmState, VM : MvvmViewModel<S>>(
    private val viewModelClass: Class<VM>
): SimpleScreen() {

    @Composable
    final override fun Content(navHostController: NavHostController?) {
        val viewModel = viewModel(modelClass = viewModelClass).apply {
            setNavController(navHostController)
        }

        Content(viewModel = viewModel)
    }

    @Composable
    protected abstract fun Content(viewModel: VM)

}