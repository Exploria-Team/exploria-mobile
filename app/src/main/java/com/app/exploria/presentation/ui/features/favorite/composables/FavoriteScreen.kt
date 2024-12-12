package com.app.exploria.presentation.ui.features.favorite.composables

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.features.common.ItemList
import com.app.exploria.presentation.ui.features.common.NavigationBottom
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.UserFavoriteViewModel

@Composable
fun FavoriteScreen(navController: NavController, modifier: Modifier = Modifier) {
    BackHandler {
        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    val favoriteViewModel: UserFavoriteViewModel = hiltViewModel()
    val favoriteList = favoriteViewModel.favorites.collectAsState().value
    val loading = favoriteViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        favoriteViewModel.getAllFavorites()
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                CustomHeaderTitle(
                    onClick = { navController.navigate(Screen.Home.route) },
                    title = "Favorite"
                )
            }
        },
        bottomBar = {
            NavigationBottom(navController)
        }
    ) { innerPadding: PaddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.surface
        ) {
            when {
                loading.value -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                favoriteList.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tidak ada Favorite di sini",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        items(favoriteList) { fav ->
                            fav?.let {
                                ItemList(
                                    navController = navController,
                                    destination = it,
                                    getId = { it?.destination?.id },
                                    getName = { it?.destination?.name },
                                    getPhotoUrls = { it?.destination?.photoUrls },
                                    favoriteViewModel = favoriteViewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
