package com.rus_artur4ik.veterinarian.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation.Companion.None
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.mvvm.BaseScreen

class AuthScreen : BaseScreen<AuthScreenState, AuthViewModel>(
    AuthViewModel::class.java
) {

    @Composable
    override fun Wrapper(viewModel: () -> AuthViewModel, content: @Composable () -> Unit) {
        content()
    }

    @Composable
    override fun Content(content: AuthScreenState, viewModel: AuthViewModel) {
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colorScheme.surface
        )
        systemUiController.setNavigationBarColor(
            color = colorResource(id = R.color.accent_bg)
        )

        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.auth_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Text(
                    text = stringResource(id = R.string.back),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 4.dp)
                        .clickable { viewModel.navigateBack() }
                        .padding(horizontal = 12.dp, vertical = 10.dp)
                )

                Text(
                    text = stringResource(id = R.string.sign_in_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                )

                OutlinedTextField(
                    value = content.email,
                    singleLine = true,
                    label = {
                        Text(text = stringResource(id = R.string.email_hint))
                    },
                    onValueChange = { viewModel.setEmail(it) },
                    maxLines = 1,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = content.password,
                    singleLine = true,
                    label = {
                        Text(text = stringResource(id = R.string.password_hint))
                    },
                    onValueChange = { viewModel.setPassword(it) },
                    maxLines = 1,
                    visualTransformation = if (content.isPasswordVisible) {
                        None
                    } else {
                        PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        val painter = painterResource(
                            id = if (content.isPasswordVisible) R.drawable.visibility else R.drawable.visibility_off
                        )

                        IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                            Icon(painter = painter, contentDescription = null)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                )

                Button(
                    onClick = { viewModel.auth() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in)
                    )
                }

                Text(
                    text = stringResource(id = R.string.forgot_password),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 16.dp)
                        .clickable { viewModel.forgotPassword() }
                        .padding(horizontal = 12.dp, vertical = 10.dp)
                )
            }
        }
    }

    @Composable
    override fun Error(throwable: Throwable, viewModel: AuthViewModel) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = "Ошибка, ${throwable.message}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error
            )

            Spacer(Modifier.height(24.dp))

            Button(onClick = { viewModel.returnToAuth() }) {
                Text("Вернуться к авторизации")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        Content(AuthViewModel())
    }
}

