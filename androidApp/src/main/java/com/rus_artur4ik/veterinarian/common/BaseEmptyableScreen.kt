package com.rus_artur4ik.veterinarian.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.petcore.mvvm.lce.LceeScreen
import com.rus_artur4ik.petcore.mvvm.lce.LceeViewModel
import com.rus_artur4ik.veterinarian.common.composables.ErrorIndicator
import com.rus_artur4ik.veterinarian.common.composables.LoadingIndicator

abstract class BaseEmptyableScreen<S, VM: LceeViewModel<S>>(
    viewModelClass: Class<VM>
): LceeScreen<S, VM>(viewModelClass) {

    @Composable
    override fun Loading(state: LceState.Loading<S>, viewModel: VM) {
        LoadingIndicator()
    }

    @Composable
    override fun Error(state: LceState.Error<S>, viewModel: VM) {
        ErrorIndicator(t = state.throwable)
    }

    @Composable
    override fun Empty(content: S, viewModel: VM) {
        EmptyIndicator()
    }

    @Composable
    override fun Wrapper(viewModel: VM, content: @Composable () -> Unit) {
        VetScreenTemplate(viewModel.navHostController) {
            content()
        }
    }
}

@Composable
@Preview
private fun EmptyIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Список пуст")
    }
}

