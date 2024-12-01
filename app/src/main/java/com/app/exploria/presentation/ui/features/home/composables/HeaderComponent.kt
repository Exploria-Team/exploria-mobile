package com.app.exploria.presentation.ui.features.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.presentation.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderComponent(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .width(254.dp)
                .height(56.dp)
                .clickable { navController.navigate(Screen.Search.route) }
        ) {
            TextField(
                value = "",
                onValueChange = { },
                placeholder = { Text("Mau kemana hari ini") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Cari destinasi"
                    )
                },
                modifier = Modifier.fillMaxSize(),
                enabled = false,
                singleLine = true,
                shape = RoundedCornerShape(40.dp),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }

        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notifikasi",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(40.dp)
                .clickable { /* Handle notification click */ }
        )

        Image(
            painter = painterResource(id = R.drawable.avatar13),
            contentDescription = "Foto Profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clickable { navController.navigate(Screen.Profile.route) }
        )
    }
}