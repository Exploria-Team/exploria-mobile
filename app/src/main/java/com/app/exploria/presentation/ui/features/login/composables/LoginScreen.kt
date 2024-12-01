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
fun LoginScreen(navController: NavController, mainViewModel: MainViewModel) {
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val isLoading by mainViewModel.isLoading.collectAsState()
    val errorMessage by mainViewModel.errorMessage.collectAsState()
    val userModel by mainViewModel.userModel.collectAsState()
    val validationError = remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
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
                        validationError.value ?: "",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                if (errorMessage != null) {
                    Text(
                        errorMessage ?: "Unknown error",
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(50.dp),
                            strokeWidth = 4.dp
                        )
                    } else {
                        CustomButton(
                            text = "Login",
                            onClick = {
                                validationError.value = null
                                if (emailState.value.text.isBlank() || passwordState.value.text.isBlank()) {
                                    validationError.value = "Email dan Password wajib diisi."
                                } else {
                                    mainViewModel.login(
                                        emailState.value.text,
                                        passwordState.value.text
                                    )
                                }
                            }
                        )
                    }

                    if (userModel?.isLogin == true) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    }

                    Text(
                        text = "Atau login menggunakan :",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    CustomButton(text = "Google")

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Belum punya akun?",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                        )

                        TextButton(onClick = {
                            navController.navigate(Screen.Register.route)
                        }) {
                            mainViewModel.clearErrorMessage()
                            Text(
                                "Register",
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
}
