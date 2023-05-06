package com.rus_artur4ik.veterinarian.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rus_artur4ik.petcore.mvvm.SimpleScreen
import com.rus_artur4ik.petcore.navigation.Navigator.navigateTo
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.VetScreen.AuthScreen

class WelcomeScreen: SimpleScreen() {

    @Composable
    override fun Content(navHostController: NavHostController?) {
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            color = colorResource(id = R.color.accent_bg)
        )
        systemUiController.setNavigationBarColor(
            color = MaterialTheme.colorScheme.surface
        )

        ConstraintLayout(
            Modifier.fillMaxSize()
        ) {
            val (logo, pets, column, background) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.welcome_screen_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .constrainAs(background) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
            )

            Image(
                painter = painterResource(id = R.drawable.welcome_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .width(152.dp)
                    .constrainAs(logo) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.welcome_pets),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(pets) {
                        top.linkTo(logo.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(column.top)
                    }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.constrainAs(column) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                )

                Text(
                    text = stringResource(id = R.string.welcome_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                )

                Button(
                    onClick = { navHostController.navigateTo(AuthScreen) },
                    modifier = Modifier
                        .padding(bottom = 42.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.go_to_auth)
                    )
                }
            }
        }
    }

    @Composable
    @Preview(showBackground = true)
    private fun Preview() {
        Content(null)
    }
}