package com.rus_artur4ik.veterinarian

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.rus_artur4ik.veterinarian.common.Navigator.NavHost
import com.rus_artur4ik.veterinarian.common.Navigator.initNavGraph
import com.rus_artur4ik.veterinarian.common.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        // Dynamic color is available on Android 12+
        val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
        val darkTheme = isSystemInDarkTheme()
        val colors = when {
            dynamicColor && darkTheme -> dynamicDarkColorScheme(this)
            dynamicColor && !darkTheme -> dynamicLightColorScheme(this)
            darkTheme -> darkColorScheme()
            else -> lightColorScheme()
        }

        MaterialTheme(colorScheme = colors) {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestinationScreen = Screen.AuthScreen) {
                initNavGraph(navController)
            }
        }
    }
}


