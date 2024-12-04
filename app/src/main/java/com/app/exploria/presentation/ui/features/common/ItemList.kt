package com.app.exploria.presentation.ui.features.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.exploria.data.remote.response.AllDestinationsItem
import com.app.exploria.presentation.ui.navigation.Screen
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ItemList(
    navController: NavController,
    destination: AllDestinationsItem? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(180.dp)
            .clickable {
                val id = destination?.id
                navController.navigate("detail/$id") {
                    launchSingleTop = true
                    popUpTo(Screen.Home.route) { inclusive = false }
                }
            }
            .clip(RoundedCornerShape(16.dp))
    ) {
        destination?.photoUrls?.let {
            GlideImage(
                imageModel = it[0],
                contentDescription = destination.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        destination?.name?.let {
            Text(
                text = it,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 10.dp, bottom = 5.dp),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }

        CustomButtonNavigation(
            icon = Icons.Filled.FavoriteBorder, modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 10.dp, top = 5.dp)
        )
    }
}
