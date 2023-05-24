package com.rus_artur4ik.petcore.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import com.rus_artur4ik.petcore.mvvm.SimpleScreen

abstract class Screen(
    val id: String,
    val arguments: List<NamedNavArgument> = listOf(),
    val screenFactory: (NavBackStackEntry) -> SimpleScreen
)