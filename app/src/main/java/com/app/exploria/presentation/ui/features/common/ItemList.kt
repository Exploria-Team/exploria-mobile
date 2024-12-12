package com.app.exploria.presentation.ui.features.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.presentation.ui.navigation.Screen
import com.app.exploria.presentation.viewModel.UserFavoriteViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun <T> ItemList(
    navController: NavController,
    destination: T?,
    modifier: Modifier = Modifier,
    getId: (T?) -> Int?,
    getName: (T?) -> String?,
    getPhotoUrls: (T?) -> List<String>?,
    favoriteViewModel: UserFavoriteViewModel
) {
    val id = getId(destination) ?: return

    val favoriteItems = favoriteViewModel.favorites.collectAsState().value
    val isFavorite = favoriteItems.any { it.destination.id == id }

    Box(
        modifier = modifier
            .size(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate("detail/$id") {
                    launchSingleTop = true
                    popUpTo(Screen.Home.route) { inclusive = false }
                }
            }
    ) {
        getPhotoUrls(destination)?.firstOrNull()?.let { photo ->
            GlideImage(
                imageModel = photo,
                contentDescription = getName(destination),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        getName(destination)?.let { name ->
            Text(
                text = name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 10.dp, bottom = 5.dp),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 10.dp, top = 5.dp)
        ) {
            CustomButtonNavigation(
                icon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                iconColor = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(40.dp)
                    .clickable(
                        onClick = {
                            favoriteViewModel.toggleFavorite(id)
                        },
                        indication = null,
                        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                    )
            )
        }
    }
}
