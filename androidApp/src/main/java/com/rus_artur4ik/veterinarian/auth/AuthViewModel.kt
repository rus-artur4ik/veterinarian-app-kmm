package com.rus_artur4ik.veterinarian.auth

import androidx.lifecycle.viewModelScope
import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.petcore.mvvm.lce.LceViewModel
import com.rus_artur4ik.veterinarian.VetScreen
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.data.VetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel : LceViewModel<AuthScreenState>() {

    private val repository = VetRepository(AppContextHolder.context)

    private var previousState = provideInitialScreenState()

    override fun provideInitialScreenState(): LceState<AuthScreenState> {
        return LceState.Content(AuthScreenState("", "", false))
    }

    fun navigateBack() {
        navHostController?.popBackStack()
    }

    fun forgotPassword() {
        // TODO
    }

    fun togglePasswordVisibility() {
        state.doIfContent {
            emitState(
                LceState.Content(it.copy(isPasswordVisible = !it.isPasswordVisible))
            )
        }
    }

    fun setEmail(newEmail: String) {
        state.doIfContent {
            emitState(
                LceState.Content(it.copy(email = newEmail))
            )
        }
    }

    fun setPassword(newPassword: String) {
        state.doIfContent {
            emitState(
                LceState.Content(it.copy(password = newPassword))
            )
        }

    }

    fun auth() {
        state.doIfContent {
            emitState(LceState.Loading())

            viewModelScope.launch(Dispatchers.IO) {
                try {
                    repository.auth(it.email, it.password)
                    navigateToHome()
                } catch (e: Exception) {
                    previousState = LceState.Content(it)
                    emitState(LceState.Error(e))
                }
            }
        }
    }

    fun returnToAuth() {
        emitState(previousState)
    }

    private fun navigateToHome() {
        navigate(VetScreen.HomeScreen)
    }
}