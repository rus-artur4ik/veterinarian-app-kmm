package com.rus_artur4ik.petcore.mvvm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

abstract class SimpleScreen {

    @Composable
    abstract fun Content(navHostController: NavHostController?)
}