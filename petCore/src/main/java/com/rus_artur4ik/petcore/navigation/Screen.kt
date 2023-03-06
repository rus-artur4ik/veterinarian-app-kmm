package com.rus_artur4ik.petcore.navigation

import com.rus_artur4ik.petcore.mvvm.SimpleScreen

abstract class Screen(val id: String, val screenFactory: () -> SimpleScreen)