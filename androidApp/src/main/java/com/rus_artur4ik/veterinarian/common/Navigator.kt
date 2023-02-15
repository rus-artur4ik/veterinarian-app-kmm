package com.rus_artur4ik.veterinarian.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rus_artur4ik.veterinarian.auth.AuthScreen
import com.rus_artur4ik.veterinarian.common.mvvm.CoreScreen
import com.rus_artur4ik.veterinarian.home.HomeScreen
import com.rus_artur4ik.veterinarian.medcard.MedCardScreen
import com.rus_artur4ik.veterinarian.mypets.MyPetsScreen
import com.rus_artur4ik.veterinarian.petinfo.PetInfoScreen

object Navigator {

    private var isInitialized = false

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
        val screenClasses = Screen::class.nestedClasses
            .asSequence()
            .filter { it.isFinal }
            .mapNotNull { it.objectInstance as? Screen }

        screenClasses.forEach {
            registerScreen(it, navHostController)
        }

        isInitialized = true
    }

    fun NavController?.navigateTo(
        screen: Screen,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        requireInitialized()
        requireNotNull(this).navigate(screen.id, navOptions, navigatorExtras)
    }

    private fun requireInitialized() {
        if (!isInitialized) {
            throw NavGraphIsUninitializedException()
        }
    }

    private fun NavGraphBuilder.registerScreen(
        screen: Screen,
        navHostController: NavHostController
    ) {
        composable(screen.id) { screen.screenFactory().Content(navHostController) }
    }

    private class NavGraphIsUninitializedException : RuntimeException(
        "You use navigation, but NavGraph is not initialized. Initialize it using NavGraphBuilder#initNavGraph before."
    )
}

sealed class Screen(val id: String, val screenFactory: () -> CoreScreen<*, *>) {
    object AuthScreen : Screen("auth", ::AuthScreen)
    object HomeScreen : Screen("main", ::HomeScreen)
    object MyPetsScreen : Screen("my_pets", ::MyPetsScreen)
    object PetInfoScreen : Screen("pet_info", ::PetInfoScreen)
    object MedCardScreen : Screen("med_card", ::MedCardScreen)
}