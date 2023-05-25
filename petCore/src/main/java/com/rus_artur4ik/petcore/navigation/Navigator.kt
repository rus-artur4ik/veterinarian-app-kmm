package com.rus_artur4ik.petcore.navigation

import androidx.annotation.MainThread
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.reflect.KClass

object Navigator {

    private const val WITH_ARGS_PLACEHOLDER = "withArgs"

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

        if (screenClasses.isEmpty()) {
            throw IllegalArgumentException("ScreensSuperclass contains no screens or it is not a sealed class!")
        }

        screenClasses.forEach {
            registerScreen(it, navHostController, it.arguments)
        }
    }

    @MainThread
    fun NavController?.navigateTo(
        screen: Screen,
        arguments: Map<String, Any> = mapOf(),
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        val currentDestination = findCurrentDestination(screen)!!

        assertAllArgsPassed(currentDestination, arguments)

        val primaryArgKey = currentDestination.route
            ?.split("$WITH_ARGS_PLACEHOLDER/")
            ?.last()
            ?.split("/")
            ?.first()

        val route = StringBuilder(screen.id)
        arguments[primaryArgKey]?.let {
            route.append("/$WITH_ARGS_PLACEHOLDER/$primaryArgKey/${it}")
        }

        val otherEntries = arguments.entries.filter { it.key != primaryArgKey }

        for (i in otherEntries.indices) {
            if (i == 0) {
                otherEntries[i].let { route.append("?${it.key}={${it.value}}") }
            } else {
                val arg = otherEntries[i]
                route.append("&${arg.key}={${arg.value}}")
            }
        }

        requireNotNull(this).navigate(route.toString(), navOptions, navigatorExtras)
    }

    private fun NavController?.findCurrentDestination(screen: Screen): NavDestination? {
        return this?.graph?.find { it.route?.startsWith(screen.id) == true }
    }

    private fun assertAllArgsPassed(
        destination: NavDestination,
        arguments: Map<String, Any>
    ) {
        val destinationArgNames = destination.arguments.keys
        destinationArgNames.all {
            val argName = arguments[it]?.javaClass?.simpleName?.lowercase()
            val destinationArgName = destination.arguments[it]?.type?.name
            if (argName == destinationArgName) {
                true
            } else {
                throw java.lang.IllegalArgumentException("Expected argument with key $it of class $destinationArgName, but got $argName}")
            }
        }
    }

    private fun NavGraphBuilder.registerScreen(
        screen: Screen,
        navHostController: NavHostController,
        arguments: List<NamedNavArgument>
    ) {
        val additionalRoute = StringBuilder()
        arguments.firstOrNull()?.let {
            additionalRoute.append("/$WITH_ARGS_PLACEHOLDER/${it.name}/{${it.name}}")
        }
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