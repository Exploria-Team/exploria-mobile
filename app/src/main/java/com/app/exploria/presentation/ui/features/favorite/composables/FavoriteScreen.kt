package com.app.exploria.presentation.ui.features.favorite.composables

import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.exploria.R
import com.app.exploria.presentation.ui.features.common.CustomHeaderTitle
import com.app.exploria.presentation.ui.features.common.ItemList
import com.example.compose.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(favorites: List<Int>, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CustomHeaderTitle(onClick = {}, title = "Favorite")
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
                modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                items(favorites) { recomendation ->
                    ItemList(image = recomendation)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    AppTheme {
        FavoriteScreen(
            favorites = listOf(
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
        )
    }
}
