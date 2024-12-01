package com.app.exploria.presentation.ui.features.register.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.presentation.ui.features.common.CustomButton
import com.app.exploria.presentation.ui.features.common.CustomTextField
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.MainViewModel

@Composable
fun RegisterScreen(navController: NavController, mainViewModel: MainViewModel) {
    val usernameState = remember { mutableStateOf(TextFieldValue()) }
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val isLoading by mainViewModel.isLoading.collectAsState()
    val errorMessage by mainViewModel.errorMessage.collectAsState()
    val isRegistered by mainViewModel.isRegistered.collectAsState()

    if (isRegistered) {
        handleSuccessfulRegistration(navController, mainViewModel)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        RegistrationCard(
            usernameState = usernameState,
            emailState = emailState,
            passwordState = passwordState,
            isLoading = isLoading,
            errorMessage = errorMessage,
            onRegisterClick = { handleRegisterClick(usernameState, emailState, passwordState, mainViewModel) },
            onLoginClick = { navController.navigate(Screen.Login.route) }
        )
    }
}

private fun handleSuccessfulRegistration(navController: NavController, mainViewModel: MainViewModel) {
    navController.navigate(Screen.Login.route) {
        popUpTo(Screen.Register.route) { inclusive = true }
    }
    mainViewModel.resetRegistrationState()
}

private fun handleRegisterClick(
    usernameState: MutableState<TextFieldValue>,
    emailState: MutableState<TextFieldValue>,
    passwordState: MutableState<TextFieldValue>,
    mainViewModel: MainViewModel
) {
    if (usernameState.value.text.isBlank() ||
        emailState.value.text.isBlank() ||
        passwordState.value.text.isBlank()
    ) {
        mainViewModel.setErrorMessage("Semua kolom wajib diisi.")
    } else {
        mainViewModel.register(
            name = usernameState.value.text,
            email = emailState.value.text,
            password = passwordState.value.text
        )
    }
}

@Composable
fun RegistrationCard(
    usernameState: MutableState<TextFieldValue>,
    emailState: MutableState<TextFieldValue>,
    passwordState: MutableState<TextFieldValue>,
    isLoading: Boolean,
    errorMessage: String?,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(0.9f),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Register", style = MaterialTheme.typography.headlineMedium)

            InputFields(usernameState, emailState, passwordState)

            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp), strokeWidth = 4.dp)
            } else {
                CustomButton(
                    text = "Register",
                    onClick = onRegisterClick
                )
            }

            LoginRow(onLoginClick)
        }
    }
}

@Composable
fun InputFields(
    usernameState: MutableState<TextFieldValue>,
    emailState: MutableState<TextFieldValue>,
    passwordState: MutableState<TextFieldValue>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        CustomTextField(
            value = usernameState.value,
            onValueChange = { usernameState.value = it },
            label = "Username",
            icon = Icons.Default.AccountCircle
        )

        CustomTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = "Email",
            icon = Icons.Default.Email
        )

        CustomTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = "Password",
            icon = Icons.Default.Lock,
            isPassword = true
        )
    }
}

@Composable
fun LoginRow(onLoginClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Sudah punya akun?",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )

        TextButton(onClick = onLoginClick) {
            Text(
                "Login",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
