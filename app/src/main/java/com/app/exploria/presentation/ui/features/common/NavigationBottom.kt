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
import androidx.navigation.NavController
import com.app.exploria.presentation.ui.navigation.Screen

@Composable
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        is Screen.Home -> Icon(Icons.Filled.Home, contentDescription = "Home")
                        is Screen.Plan -> Icon(Icons.Outlined.Place, contentDescription = "Plan")
                        is Screen.Favorite -> Icon(Icons.Outlined.Favorite, contentDescription = "Favorite")
                        else -> {}
                    }
                },
                label = {
                    when (screen) {
                        is Screen.Home -> Text("Home")
                        is Screen.Plan -> Text("Rencana")
                        is Screen.Favorite -> Text("Disukai")
                        else -> {}
                    }
                },
                selected = navController.currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


