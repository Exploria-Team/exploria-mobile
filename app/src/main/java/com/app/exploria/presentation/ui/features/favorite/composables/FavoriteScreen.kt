package com.app.exploria.presentation.ui.features.favorite.composables

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.R
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.features.common.ItemList
import com.app.exploria.presentation.ui.features.common.NavigationBottom
import com.app.exploria.presentation.ui.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(navController: NavController, modifier: Modifier = Modifier) {
    BackHandler {
        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    val favorites = listOf(
        R.drawable.img,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2,
        R.drawable.img2
    )

    Scaffold(
        topBar = {
            CustomHeaderTitle(onClick = {}, title = "Favorite")
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                items(favorites) { recomendation ->
                    ItemList(navController, image = recomendation)
                }
            }
        }
    }
}
