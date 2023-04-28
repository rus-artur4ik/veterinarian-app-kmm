package com.rus_artur4ik.veterinarian.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun VetScreenTemplate(
    navController: NavController?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = modifier.weight(1f)
        ) {
            content()
        }

        BottomNavBar(
            navController = navController
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun VetScreenTemplatePreview() {
    VetScreenTemplate(navController = null) {
    }
}