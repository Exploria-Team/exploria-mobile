package com.app.exploria.presentation.ui.features.profile.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.MainViewModel
import com.app.exploria.presentation.viewModel.ProfileViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileScreen(
    navController: NavController, mainViewModel: MainViewModel
) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val userData by profileViewModel.userData.collectAsState()
    val user by mainViewModel.user.collectAsState()
    val userModel by mainViewModel.userModel.collectAsState()

    LaunchedEffect(user) {
        user?.let {
            profileViewModel.getDataUser(it.id)
        }
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                CustomHeaderTitle(
                    onClick = { navController.popBackStack() },
                    title = "Profile"
                )
            }
        },
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
                if (userData?.profilePictureUrl.isNullOrEmpty()) {
                    Image(
                        painter = painterResource(id = R.drawable.profiledefault),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .size(130.dp)
                            .clip(CircleShape)
                    )
                } else {
                    userModel?.let {
                        it.profilePictureUrl?.let { data ->
                            GlideImage(
                                imageModel = data,
                                contentDescription = userModel!!.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .size(130.dp)
                                    .clip(CircleShape)
                            )
                        }
                    }
                }
                userModel?.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )
                }

                SettingsListItem(title = "Ubah Profile", icon = R.drawable.create, onClick = {
                    navController.navigate(Screen.ProfileForm.route)
                })

                SettingsListItem(title = "FAQ", icon = R.drawable.faq)

                SettingsListItem(title = "Bantuan", icon = R.drawable.help)

                SettingsListItem(title = "Keluar", icon = R.drawable.logout, onClick = {
                    mainViewModel.logout()

                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Profile.route) { inclusive = true }
                        launchSingleTop = true
                    }
                })
            }
        }
    }
}

