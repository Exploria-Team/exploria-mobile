package com.app.exploria.presentation.ui.features.profile.composables

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.presentation.ui.features.common.CustomButton
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.features.common.CustomTextField
import com.app.exploria.presentation.viewModel.MainViewModel
import com.app.exploria.presentation.viewModel.ProfileViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Composable
fun ProfileForm(navController: NavController, mainViewModel: MainViewModel) {
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val dateState = remember { mutableStateOf(TextFieldValue()) }
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val user by mainViewModel.userModel.collectAsState()
    val profilePictureUri = remember { mutableStateOf<Uri?>(null) }

    val coroutineScope = rememberCoroutineScope()

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            profilePictureUri.value = uri
        }

    LaunchedEffect(user) {
        user?.let { data ->
            nameState.value = TextFieldValue(data.name)
            emailState.value = TextFieldValue(data.email)
            dateState.value = TextFieldValue("")
        }
    }

    val onSaveProfile = {
        coroutineScope.launch {
            profileViewModel.updateDataUser(
                id = user?.id ?: 0,
                name = nameState.value.text.takeIf { it.isNotBlank() },
                email = emailState.value.text.takeIf { it.isNotBlank() },
                profilePictureUri = profilePictureUri.value,
                age = dateState.value.text.takeIf { it.isNotBlank() }
            )
        }
    }

    val updateResult by profileViewModel.updateDataUserResult.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(updateResult) {
        updateResult?.let { result ->
            if (result.isSuccess) {
                result.getOrNull()?.let { updatedData ->
                    val updatedUser = user?.copy(
                        name = updatedData.name ?: user!!.name,
                        email = updatedData.email ?: user!!.email,
                        profilePictureUrl = updatedData.profilePictureUrl ?: user!!.profilePictureUrl
                    )
                    updatedUser?.let { mainViewModel.saveSession(it) }
                }

                snackbarHostState.showSnackbar("Profil berhasil diperbarui!")
                navController.popBackStack()
            } else {
                snackbarHostState.showSnackbar("Gagal memperbarui profil: ${result.exceptionOrNull()?.message}")
            }
        }
    }

    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(vertical = 16.dp)) {
                CustomHeaderTitle(
                    onClick = { navController.popBackStack() },
                    title = "Profile"
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (profilePictureUri.value == null && user?.profilePictureUrl.isNullOrEmpty()) {
                    Image(
                        painter = painterResource(id = R.drawable.profiledefault),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .size(130.dp)
                            .clip(CircleShape)
                    )
                } else {
                    val imageUri = profilePictureUri.value?.toString() ?: user?.profilePictureUrl
                    imageUri?.let {
                        GlideImage(
                            imageModel = it,
                            contentDescription = user?.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .size(130.dp)
                                .clip(CircleShape)
                        )
                    }
                }

                Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                    Text("Change Profile Picture")
                }

                Spacer(modifier = Modifier.height(16.dp))


                CustomTextField(
                    value = nameState.value,
                    onValueChange = { nameState.value = it },
                    label = "Username",
                    icon = Icons.Default.AccountCircle
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = emailState.value,
                    onValueChange = { emailState.value = it },
                    label = "Email",
                    icon = Icons.Default.Email
                )

                Spacer(modifier = Modifier.height(16.dp))


                CustomTextField(
                    value = dateState.value,
                    onValueChange = { dateState.value = it },
                    label = "Date",
                    icon = Icons.Default.DateRange
                )
            }

            CustomButton("Simpan", onClick = { onSaveProfile() })
        }
    }
}
