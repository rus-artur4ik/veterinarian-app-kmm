package com.rus_artur4ik.veterinarian.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.rus_artur4ik.petcore.navigation.Navigator.navigateTo
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.VetScreen

@Composable
fun VetScreenTemplate(
    navController: NavController?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                content()
            }
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController?.navigateTo(VetScreen.AppointmentSelectorScreen)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.make_appointment_icon),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun VetScreenTemplatePreview() {
    VetScreenTemplate(navController = null) {
    }
}