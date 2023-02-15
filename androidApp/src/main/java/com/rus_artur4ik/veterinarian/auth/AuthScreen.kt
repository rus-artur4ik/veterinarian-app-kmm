package com.rus_artur4ik.veterinarian.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.rus_artur4ik.petcore.mvvm.CoreScreen
import com.rus_artur4ik.petcore.navigation.Navigator.navigateTo
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.VetScreen.HomeScreen

class AuthScreen : CoreScreen<AuthScreenState, AuthViewModel>(
    AuthViewModel::class.java
) {

    companion object {
        private const val TITLE_WIDTH_FRACTION = 0.8f
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(viewModel: AuthViewModel, navHostController: NavHostController?) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(id = R.string.sign_in_title),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth(TITLE_WIDTH_FRACTION)
                        .padding(horizontal = dimensionResource(R.dimen.default_horizontal_padding))
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(Dp(12f)))

                Text(
                    text = stringResource(id = R.string.sign_in_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth(TITLE_WIDTH_FRACTION)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(Dp(12f)))

                TextField(
                    value = "",
                    singleLine = true,
                    label = {
                        Text(text = stringResource(id = R.string.email_hint))
                    },
                    onValueChange = {},
                )

                Spacer(modifier = Modifier.height(Dp(12f)))

                TextField(
                    value = "",
                    singleLine = true,
                    label = {
                        Text(text = stringResource(id = R.string.password_hint))
                    },
                    onValueChange = {},
                )

                Spacer(modifier = Modifier.height(Dp(12f)))

                Button(
                    onClick = { navHostController?.navigateTo(HomeScreen) }
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in),
                        modifier = Modifier.padding(
                            horizontal = Dp(48f)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(Dp(12f)))

                Text(
                    text = stringResource(id = R.string.forgot_password_or_no_account),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        Content()
    }
}

