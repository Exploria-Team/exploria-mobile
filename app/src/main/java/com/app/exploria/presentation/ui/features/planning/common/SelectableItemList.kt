package com.app.exploria.presentation.ui.features.planning.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.app.exploria.presentation.ui.features.common.CustomButtonNavigation
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun <T> SelectableItemList(
    destination: T?,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onSelect: (T?) -> Unit,
    getId: (T?) -> Int?,
    getName: (T?) -> String?,
    getPhotoUrls: (T?) -> List<String>?
) {
    Box(
        modifier = modifier
            .size(180.dp)
            .clickable { onSelect(destination) }
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent)
    ) {
        getPhotoUrls(destination)?.let { photos ->
            if (photos.isNotEmpty()) {
                GlideImage(
                    imageModel = photos[0],
                    contentDescription = getName(destination),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
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

        if (isSelected) {
            CustomButtonNavigation(
                icon = Icons.Default.Check, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 10.dp, top = 5.dp)
            )
        }
    }
}
