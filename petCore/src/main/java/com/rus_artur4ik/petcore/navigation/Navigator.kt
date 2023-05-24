package com.rus_artur4ik.petcore.navigation

import androidx.annotation.MainThread
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
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
            registerScreen(it, navHostController, it.arguments)
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
            registerScreen(it, navHostController, it.arguments)
        }
    }

    @MainThread
    fun NavController?.navigateTo(
        screen: Screen,
        arguments: List<Pair<String, Any>> = listOf(),
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        val route = StringBuilder(screen.id)
        arguments.firstOrNull()?.let { route.append("/${it.second}") }
        arguments.getOrNull(1)?.let { route.append("?${it.first}={${it.second}}") }
        if (arguments.size > 2) {
            for (i in 2 until arguments.size) {
                val arg = arguments[i]
                route.append("&${arg.first}={${arg.second}}")
            }
        }

        requireNotNull(this).navigate(route.toString(), navOptions, navigatorExtras)
    }

    private fun NavGraphBuilder.registerScreen(
        screen: Screen,
        navHostController: NavHostController,
        arguments: List<NamedNavArgument>
    ) {
        val additionalRoute = StringBuilder()
        arguments.firstOrNull()?.let { additionalRoute.append("/{${it.name}}") }
        arguments.getOrNull(1)?.let { additionalRoute.append("?${it.name}={${it.name}}") }
        if (arguments.size > 2) {
            for (i in 2 until arguments.size) {
                val name = arguments[i].name
                additionalRoute.append("&$name={$name}")
            }
        }

        composable("${screen.id}$additionalRoute", arguments = arguments) { backStackEntry ->
            screen.screenFactory(backStackEntry).Content(navHostController)
        }
    }
}