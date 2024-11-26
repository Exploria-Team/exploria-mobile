package com.app.exploria.presentation.ui.features.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun NavigationBottom() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home"
                )
            },
            label = {
                Text("Home")
            },
            selected = selectedIndex == 0,
            onClick = {
                selectedIndex = 0
            }
        )

        // Plan Item
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Outlined.Place,
                    contentDescription = "Plan"
                )
            },
            label = {
                Text("Rencana")
            },
            selected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
            }
        )

        // Favorite Item
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Outlined.Favorite,
                    contentDescription = "Favorite"
                )
            },
            label = {
                Text("Disukai")
            },
            selected = selectedIndex == 2,
            onClick = {
                selectedIndex = 2
            }
        )
    }
}
