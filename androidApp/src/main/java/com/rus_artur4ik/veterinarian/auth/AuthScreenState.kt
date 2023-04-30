package com.rus_artur4ik.veterinarian.auth

import com.rus_artur4ik.petcore.mvvm.MvvmState

data class AuthScreenState(
    val email: String,
    val password: String,
    val isPasswordVisible: Boolean
): MvvmState