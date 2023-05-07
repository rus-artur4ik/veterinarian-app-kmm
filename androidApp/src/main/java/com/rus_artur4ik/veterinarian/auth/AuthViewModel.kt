package com.rus_artur4ik.veterinarian.auth

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.VetScreen
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.common.mvvm.BaseViewModel
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.data.exception.WrongCredentialsException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class AuthViewModel : BaseViewModel<AuthScreenState>() {

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
                    withContext(Dispatchers.Main) {
                        navigateToHome()
                    }
                } catch (e: WrongCredentialsException) {
                    previousState = LceState.Content(it)
                    emitState(
                        LceState.Error(
                            IOException(resources.getString(R.string.wrong_login_or_password))
                        )
                    )
                } catch (e: Exception) {
                    Firebase.crashlytics.recordException(e)
                    Log.e("Auth", e.stackTraceToString())
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