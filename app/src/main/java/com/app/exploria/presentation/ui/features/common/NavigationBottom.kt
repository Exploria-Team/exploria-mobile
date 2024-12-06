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
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.data.models.userData.UserModel
import com.app.exploria.presentation.ui.navigation.Screen

@Composable
fun NavigationBottom(navController: NavController, user: UserModel? = null) {
    val items = listOf(
        Screen.Home,
        Screen.Plan,
        Screen.Favorite,
        Screen.Guide
    )

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        is Screen.Home -> Icon(Icons.Filled.Home, contentDescription = "Home")
                        is Screen.Plan -> Icon(Icons.Outlined.Place, contentDescription = "Plan")
                        is Screen.Favorite -> Icon(
                            Icons.Outlined.Favorite,
                            contentDescription = "Favorite"
                        )
                        is Screen.Guide -> Icon(
                            painter = painterResource(id = R.drawable.explore),
                            contentDescription = "Guide"
                        ) else -> {}
                    }
                },
                label = {
                    when (screen) {
                        is Screen.Home -> Text("Home")
                        is Screen.Plan -> Text("Rencana")
                        is Screen.Favorite -> Text("Favorite")
                        is Screen.Guide -> Text("Guide")
                        else -> {}
                    }
                },
                selected = navController.currentDestination?.route == screen.route,
                onClick = {
                    if (screen == Screen.Plan || screen == Screen.Favorite) {
                        if (user?.isLogin == true) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        } else {
                            navController.navigate(Screen.Login.route) {
                                launchSingleTop = true
                            }
                        }
                    } else {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
