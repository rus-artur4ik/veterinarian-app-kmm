package com.rus_artur4ik.petcore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.reflect.KClass

object Navigator {

    @Composable
    fun NavHost(
        startDestinationScreen: Screen,
        screens: List<Screen>,
        modifier: Modifier = Modifier,
        route: String? = null,
    ) {
        val navController = rememberNavController()

        NavHost(
            navController,
            startDestinationScreen.id,
            modifier,
            route
        ) {
            initNavGraph(navController, screens)
        }
    }

    fun NavGraphBuilder.initNavGraph(
        navHostController: NavHostController,
        screens: List<Screen>
    ) {
        screens.forEach {
            registerScreen(it, navHostController)
        }
    }

    /**
     * @param screensSuperclass - sealed class containing all screens
     */
    @Composable
    fun AutowiredNavHost(
        startDestinationScreen: Screen,
        screensSuperclass: KClass<out Screen>,
        modifier: Modifier = Modifier,
        route: String? = null,
    ) {
        val navController = rememberNavController()

        NavHost(
            navController,
            startDestinationScreen.id,
            modifier,
            route
        ) {
            initNavGraph(navController, screensSuperclass)
        }
    }

    fun NavGraphBuilder.initNavGraph(
        navHostController: NavHostController,
        screensSuperclass: KClass<out Screen>
    ) {
        val screenClasses = screensSuperclass.nestedClasses
            .filter { it.isFinal }
            .mapNotNull { it.objectInstance as? Screen }

        if(screenClasses.isEmpty()) {
            throw IllegalArgumentException("ScreensSuperclass contains no screens or it is not a sealed class!")
        }

        screenClasses.forEach {
            registerScreen(it, navHostController)
        }
    }

    fun NavController?.navigateTo(
        screen: Screen,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        requireNotNull(this).navigate(screen.id, navOptions, navigatorExtras)
    }

    private fun NavGraphBuilder.registerScreen(
        screen: Screen,
        navHostController: NavHostController
    ) {
        composable(screen.id) { screen.screenFactory().Content(navHostController) }
    }
}