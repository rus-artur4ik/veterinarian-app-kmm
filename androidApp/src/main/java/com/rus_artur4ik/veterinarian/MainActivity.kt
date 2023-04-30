package com.rus_artur4ik.veterinarian

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rus_artur4ik.petcore.navigation.Navigator.AutowiredNavHost
import com.rus_artur4ik.veterinarian.VetScreen.WelcomeScreen
import com.rus_artur4ik.veterinarian.common.AppContextHolder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContextHolder.context = application

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

        rememberSystemUiController().setSystemBarsColor(
            color = colors.surface
        )

        MaterialTheme(colorScheme = colors) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.surface)
            ) {
                AutowiredNavHost(startDestinationScreen = WelcomeScreen, VetScreen::class)
            }
        }
    }
}


