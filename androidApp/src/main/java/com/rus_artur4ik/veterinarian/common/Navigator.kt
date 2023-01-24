package com.rus_artur4ik.veterinarian.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rus_artur4ik.veterinarian.auth.AuthScreen
import com.rus_artur4ik.veterinarian.common.mvvm.CoreScreen
import com.rus_artur4ik.veterinarian.homescreen.HomeScreen
import com.rus_artur4ik.veterinarian.mypets.MyPetsScreen

object Navigator {

    @Composable
    fun NavHost(
        navController: NavHostController,
        startDestinationScreen: Screen,
        modifier: Modifier = Modifier,
        route: String? = null,
        builder: NavGraphBuilder.() -> Unit
    ) = NavHost(
        navController,
        startDestinationScreen.id,
        modifier,
        route,
        builder
    )

    fun NavGraphBuilder.initNavGraph(navHostController: NavHostController) {
        registerScreen(Screen.AuthScreen, navHostController)
        registerScreen(Screen.HomeScreen, navHostController)
        registerScreen(Screen.MyPetsScreen, navHostController)
    }

    fun NavHostController.navigateTo(
        screen: Screen,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        navigate(screen.id, navOptions, navigatorExtras)
    }

    private fun NavGraphBuilder.registerScreen(
        screen: Screen,
        navHostController: NavHostController
    ) {
        composable(screen.id) { screen.screenFactory().Content(navHostController) }
    }
}

sealed class Screen(val id: String, val screenFactory: () -> CoreScreen<*, *>) {
    object AuthScreen : Screen("auth", ::AuthScreen)
    object HomeScreen : Screen("main", ::HomeScreen)
    object MyPetsScreen : Screen("my_pets", ::MyPetsScreen)
}