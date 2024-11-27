package com.app.exploria.presentation.ui.features.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ItemList(navController: NavController, image: Int, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .size(180.dp)
        .clickable {
            val id = "123"
            navController.navigate("detail/$id")
        }
        .clip(RoundedCornerShape(16.dp))) {
        Image(
            painter = painterResource(id = image),
            "itemList",
            modifier = Modifier.size(180.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Pantai Kuta - Bali",
            modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, bottom = 5.dp),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
        CustomButtonNavigation(
            icon = Icons.Filled.FavoriteBorder, modifier = Modifier
                .align(
                    Alignment.TopEnd
                )
                .padding(end = 10.dp, top = 5.dp)
        )
    }
}

