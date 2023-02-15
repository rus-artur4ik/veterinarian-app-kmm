package com.rus_artur4ik.veterinarian.auth

import com.rus_artur4ik.petcore.mvvm.CoreViewModel

class AuthViewModel : CoreViewModel<AuthScreenState>() {

    override fun provideInitialScreenState(): AuthScreenState {
        return AuthScreenState()
    }
}