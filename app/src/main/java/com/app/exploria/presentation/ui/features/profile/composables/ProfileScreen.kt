package com.app.exploria.presentation.ui.features.profile.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.exploria.R
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.features.common.NavigationBottom
import com.example.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(
    onBackClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onFAQClick: () -> Unit,
    onHelpClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomHeaderTitle(onClick = {}, title = "Profile")
        },
        bottomBar = {
            NavigationBottom()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture and Name
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .size(130.dp)
                        .clip(CircleShape)
                )

                Text(text = "Rizki Sepriadi", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 32.dp))
            }

            SettingsListItem(title = "Ubah Profile")

            SettingsListItem(title = "FAQ")

            SettingsListItem(title = "Bantuan")

            SettingsListItem(title = "Keluar")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    AppTheme {
        ProfilePage(
            onBackClick = {},
            onEditProfileClick = {},
            onFAQClick = {},
            onHelpClick = {},
            onLogoutClick = {}
        )
    }
}

