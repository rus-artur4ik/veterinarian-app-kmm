package com.rus_artur4ik.veterinarian.auth

import com.rus_artur4ik.petcore.mvvm.MvvmViewModel
import com.rus_artur4ik.veterinarian.VetScreen

class AuthViewModel : MvvmViewModel<AuthScreenState>() {

    override fun provideInitialScreenState(): AuthScreenState {
        return AuthScreenState()
    }

    fun navigateToHome() {
        navigate(VetScreen.HomeScreen)
    }
}