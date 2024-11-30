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
    val validationError = remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
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
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.headlineMedium
                )

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

                if (!validationError.value.isNullOrEmpty()) {
                    Text(
                        text = validationError.value ?: "",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                if (errorMessage != null) {
                    Text(
                        text = errorMessage ?: "",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp), strokeWidth = 4.dp)
                    } else {
                        CustomButton(
                            text = "Register",
                            onClick = {
                                validationError.value = null
                                if (usernameState.value.text.isBlank() ||
                                    emailState.value.text.isBlank() ||
                                    passwordState.value.text.isBlank()
                                ) {
                                    validationError.value = "Semua kolom wajib diisi."
                                } else {
                                    mainViewModel.register(
                                        name = usernameState.value.text,
                                        email = emailState.value.text,
                                        password = passwordState.value.text
                                    )
                                }
                            }
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Sudah punya akun?",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                    )

                    TextButton(onClick = { navController.navigate(Screen.Login.route) }) {
                        Text(
                            "Login",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}
