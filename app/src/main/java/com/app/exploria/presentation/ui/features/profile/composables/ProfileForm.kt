package com.app.exploria.presentation.ui.features.profile.composables

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@Composable
fun ProfileForm(navController: NavController, mainViewModel: MainViewModel) {
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val dateState = remember { mutableStateOf(TextFieldValue()) }
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val userData by profileViewModel.userData.collectAsState()
    val user by mainViewModel.user.collectAsState()
    val profilePictureUri = remember { mutableStateOf<String?>(null) }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            profilePictureUri.value = uri?.toString()
        }

    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(vertical = 16.dp)) {
                CustomHeaderTitle(
                    onClick = { navController.popBackStack() },
                    title = "Profile"
                )
            }
        }
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
                if (profilePictureUri.value.isNullOrEmpty() && user?.profilePictureUrl.isNullOrEmpty()) {
                    Image(
                        painter = painterResource(id = R.drawable.profiledefault),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .size(130.dp)
                            .clip(CircleShape)
                    )
                } else {
                    (profilePictureUri.value ?: user?.profilePictureUrl)?.let {
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

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                    Text("Change Profile Picture")
                }


                    CustomTextField(
                        value = nameState.value,
                        onValueChange = { nameState.value = it },
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
                        value = dateState.value,
                        onValueChange = { dateState.value = it },
                        label = "Date",
                        icon = Icons.Default.DateRange
                    )
                }
                CustomButton("Simpan")
            }
        }
    }
}
